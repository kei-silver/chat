<!doctype html>
<html lang="en">
<head>
    <title>Websocket ChatRoom</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="/webjars/bootstrap/4.3.1/dist/css/bootstrap.min.css">
    <style>
        [v-cloak] {
            display: none;
        }
    </style>
</head>
<body>
<div class="container" id="app" v-cloak>
    <div>
        <h2>{{room.name}}</h2>
    </div>
    <div class="input-group">
        <div class="input-group-prepend">
            <label class="input-group-text">내용</label>
        </div>
        <input type="text" class="form-control" v-model="message" v-on:keypress.enter="sendMessage">
        <div class="input-group-append">
            <button class="btn btn-primary" type="button" @click="sendMessage">보내기</button>
            <button class="btn btn-success" v-if="!isSending" @click="startSending">부하 테스트 시작</button>
            <button class="btn btn-danger" v-if="isSending" @click="stopSending">중지</button>
        </div>
    </div>
    <ul class="list-group">
        <li class="list-group-item" v-for="message in messages">
            {{message.senderName}} - {{message.message}} - {{new Date(message.createdAt)}}</a>
        </li>
    </ul>
    <div></div>
</div>
<!-- JavaScript -->
<script src="/webjars/vue/2.5.16/dist/vue.min.js"></script>
<script src="/webjars/axios/0.17.1/dist/axios.min.js"></script>
<script src="/webjars/sockjs-client/1.1.2/sockjs.min.js"></script>
<script src="/webjars/stomp-websocket/2.3.3-1/stomp.min.js"></script>

<script>
    var sock = new SockJS("/ws/chat");
    var ws = Stomp.over(sock);
    var reconnect = 0;

    var intervalId = null;
    var isSending = false;

    var vm = new Vue({
        el: '#app',
        data: {
            roomId: '',
            type: '',
            room: {},
            senderName: '',
            message: '',
            messages: [],
            sequenceNumber: 0,
            messageQueue: [],
            isSending: false,
        },
        created() {
            this.roomId = localStorage.getItem('wschat.roomId');
            this.type = localStorage.getItem('wschat.type');
            this.senderName = localStorage.getItem('wschat.sender');
            this.findRoom();
        },
        methods: {
            findRoom: function () {
                axios.get('/api/v1/chat/room/' + this.roomId).then(response => {
                    this.room = response.data;
                });
            },
            sendMessage: function () {
                this.sequenceNumber++;

                if(this.type === "GROUP"){
                    ws.send("/pub/chat/room/" + this.roomId + "/type/group", {}, JSON.stringify({
                        type: 'COMMENT',
                        senderName: this.senderName,
                        message: this.message,
                        sequenceNumber: this.sequenceNumber
                    }));
                }else{
                    ws.send("/pub/chat/room/" + this.roomId , {}, JSON.stringify({
                        type: 'COMMENT',
                        senderName: this.senderName,
                        message: this.message,
                        sequenceNumber: this.sequenceNumber
                    }));
                }

                this.message = '';
            },
            recvMessage: function (recv) {
                const receivedSeqNumber = recv.sequenceNumber;
                const currentSeqNumber = this.sequenceNumber;

                if (receivedSeqNumber === currentSeqNumber) {
                    this.messages.unshift({
                        "type": recv.type,
                        "senderName": recv.senderName,
                        "message": recv.message,
                        "createdAt": recv.createdAt
                    });
                    this.sequenceNumber = recv.sequenceNumber;
                } else{
                    this.messageQueue.push(recv);
                    console.log(this.messageQueue)
                    this.messageQueue.sort((a, b) => a.sequenceNumber - b.sequenceNumber);
                    while (this.messageQueue.length > 0 && this.messageQueue[0].sequenceNumber === currentSeqNumber) {
                        const message = this.messageQueue.shift();
                        this.messages.unshift({
                            "type": message.type,
                            "senderName": message.senderName,
                            "message": message.message,
                            "createdAt": message.createdAt
                        });
                        this.sequenceNumber = message.sequenceNumber;
                    }
                }
            },
            startSending: function () {
                if (!this.isSending) {
                    this.isSending = true;
                    var testMessages = [
                        "Test message 1",
                        "Test message 2",
                        "Test message 3",
                        "Test message 4",
                        "Test message 5",
                        "Test message 6",
                        "Test message 7",
                        "Test message 8",
                        "Test message 9",
                        "Test message 10"
                    ];
                    var count = 0;
                    intervalId = setInterval(function () {
                        if (count < testMessages.length) {
                            // Send each test message from the array
                            vm.message = testMessages[count];
                            vm.sendMessage();
                            count++;
                        } else {
                            // Stop sending messages after all test messages are sent
                            clearInterval(intervalId);
                            vm.isSending = false;
                        }
                    }, 100); // Adjust the interval time if needed (1 second = 1000 milliseconds)
                }
            },
            stopSending: function () {
                if (this.isSending) {
                    this.isSending = false;
                    clearInterval(intervalId);
                }
            }
        }
    });

    function connect() {
        // pub/sub event
        ws.connect({}, function (frame) {
        var subscriptionId = 'sub-' + Math.random(); // 이거 uuid로 바꾸기
            ws.subscribe("/sub/chat/room/" + vm.$data.roomId, function (message) {
                var recv = JSON.parse(message.body);
                console.log(recv);
                vm.recvMessage(recv);
            },{ id: subscriptionId,
            // 순서 보장을 위해 소켓 옵션에 acknowledge를 명시적으로 설정 (ACK 자동 처리)
            ack: 'auto'
            });


            ws.send("/pub/chat/room/" + vm.$data.roomId, {"content-type": "application/json"}, JSON.stringify({
                type: 'ENTER',
                senderName: vm.$data.senderName,
                message: '',
                sequenceNumber: 0
            }));

        }, function (error) {
            if (reconnect++ <= 5) {
                setTimeout(function () {
                    console.log("connection reconnect");
                    sock = new SockJS("/ws/chat");
                    ws = Stomp.over(sock);
                    connect();
                }, 10 * 1000);
            }
        });
    }

    connect();
</script>
</body>
</html>
