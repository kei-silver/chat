<!DOCTYPE html>
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

        /* Chatroom container style */
        .chat-container {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
        }

        /* Chat header style */
        .chat-header {
            text-align: center;
            font-size: 20px;
            font-weight: bold;
            margin-bottom: 20px;
        }

        /* Chat messages container style */
        .chat-messages {
            max-height: 700px; /* Increase this value to display more chat messages before the scrollbar appears */
            overflow-y: auto;
            display: flex;
            flex-direction: column-reverse; /* Reverse the order of chat messages */
        }

        /* Each chat message style */
        .chat-message {
            padding: 5px; /* Reduce padding for each chat message */
            margin-bottom: 5px;
            border-radius: 10px;
            font-size: 14px; /* Reduce font size for chat message text */
        }

        /* Chat input style */
        .chat-input {
            display: flex;
            align-items: center;
            margin-top: 20px;
        }

        .chat-input input {
            flex: 1;
            padding: 10px;
            border-radius: 20px;
            border: 1px solid #ccc;
            outline: none;
        }

        .chat-input button {
            margin-left: 10px;
        }

        /* Sender's chat message style */
        .chat-message-sender {
            align-self: flex-end; /* Align the sender's message to the right side */
            background-color: #DCF8C6; /* Light green background for the sender's message */
        }

        /* Receiver's chat message style */
        .chat-message-receiver {
            align-self: flex-start; /* Align the receiver's message to the left side */
            background-color: #F2F2F2; /* Light gray background for the receiver's message */
        }

        /* Search bar style */
        .search-bar {
            display: flex;
            align-items: center;
            margin-bottom: 10px;
        }

        .search-bar input {
            flex: 1;
            padding: 10px;
            border-radius: 20px;
            border: 1px solid #ccc;
            outline: none;
        }

        .search-bar button {
            margin-left: 10px;
        }

    </style>
</head>
<body>
<div class="container" id="app" v-cloak>
    <div class="chat-container">
        <div class="chat-header">{{room.name}}</div>

        <!-- Search bar -->
        <div class="search-bar">
            <input type="text" class="form-control" v-model="searchKeyword" @keyup.enter="searchMessage" placeholder="대화를 검색하세요...">
            <button class="btn btn-primary" @click="searchMessage">검색</button>
        </div>

        <div class="chat-messages">
            <div :class="{'chat-message': true, 'chat-message-sender': message.senderName === senderName, 'chat-message-receiver': message.senderName !== senderName}" v-for="message in messages">
                <strong>{{message.senderName}}</strong><br>
                {{message.message}}<br>
                <small>{{new Date(message.createdAt)}}</small>
            </div>
        </div>
        <div class="chat-input">
            <input type="text" class="form-control" v-model="message" v-on:keypress.enter="sendMessage" placeholder="메시지를 입력하세요...">
            <button class="btn btn-primary" @click="sendMessage">보내기</button>
            <button class="btn btn-success" v-if="!isSending" @click="startSending">부하 테스트 시작</button>
            <button class="btn btn-danger" v-if="isSending" @click="stopSending">중지</button>
        </div>
    </div>
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
            searchKeyword: '', // 검색어를 담을 변수 추가
        },
        created() {
            this.roomId = localStorage.getItem('wschat.roomId');
            this.type = localStorage.getItem('wschat.type');
            this.senderName = localStorage.getItem('wschat.sender');
            this.findRoom();
        },
        methods: {
            findRoom: function () {
                axios.get('/api/chat/room/' + this.roomId).then(response => {
                    this.room = response.data;
                });
            },
            sendMessage: function () {
                this.sequenceNumber++;

                if(this.type === "GROUP"){
                    ws.send("/pub/chat/room/" + this.roomId + "/type/group", {}, JSON.stringify({
                        type: 'COMMENT',
                        senderId: 1, // *****
                        senderName: this.senderName,
                        message: this.message,
                        sequenceNumber: this.sequenceNumber,
                        chatRoomId: this.roomId,
                        createdAt: Date.now()
                    }));
                }else{
                    ws.send("/pub/chat/room/" + this.roomId , {}, JSON.stringify({
                        type: 'COMMENT',
                        senderId: 1, // *****
                        senderName: this.senderName,
                        message: this.message,
                        sequenceNumber: this.sequenceNumber,
                        chatRoomId: this.roomId,
                        createdAt: Date.now()
                    }));
                }

                this.message = '';
            },
            recvMessage: function (recv) {

                this.messages.unshift({
                    "type": recv.type,
                    "senderName": recv.senderName,
                    "message": recv.message,
                    "createdAt": recv.createdAt
                });

                // createdAt 값으로 내림차순 정렬
                this.messages.sort((a, b) => b.createdAt - a.createdAt);

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
                    }, 10); // Adjust the interval time if needed (1 second = 1000 milliseconds)
                }
            },

            stopSending: function () {
                if (this.isSending) {
                    this.isSending = false;
                    clearInterval(intervalId);
                }
            },

            searchMessage: function() {
                // 검색어에 해당하는 메시지를 서버로부터 받아와 화면에 표시하는 로직을 추가할 수 있습니다.
                // 검색 로직을 구현하려면 서버 쪽과 API를 통해 검색 결과를 요청하고 받아와야 합니다.
                // 이 예제에서는 검색어만을 담는 변수인 this.searchKeyword만 사용하고 있으므로,
                // 실제로 검색 기능을 추가하려면 서버 API와의 통신 등을 구현해야 합니다.
                console.log("검색어: " + this.searchKeyword);
                // 여기에 검색 기능을 구현하는 로직을 추가하세요.
                // 서버로 검색어를 전송하고, 검색 결과를 받아와서 messages 배열을 업데이트하거나
                // 검색 결과를 따로 표시하는 방식으로 구현할 수 있습니다.
            }
        }
    });

    function connect() {
        // pub/sub event
        ws.connect({}, function (frame) {
        console.log(frame);

        var subscriptionId = 'sub-' + Math.random(); // 이거 uuid로 바꾸기
            ws.subscribe("/topic/group/room/" + vm.$data.roomId, function (message) {
                var recv = JSON.parse(message.body);
                console.log(recv);
                vm.recvMessage(recv);
            },{ id: subscriptionId,
            // 순서 보장을 위해 소켓 옵션에 acknowledge를 명시적으로 설정 (ACK 자동 처리)
            ack: 'auto'
            });


            ws.send("/pub/chat/room/" + vm.$data.roomId + "/enter", {"content-type": "application/json"}, JSON.stringify({
                type: 'ENTER',
                senderId: 1, // *****
                senderName: vm.$data.senderName,
                message: '',
                sequenceNumber: 0,
                chatRoomId: vm.$data.roomId,
                createdAt: Date.now()
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
