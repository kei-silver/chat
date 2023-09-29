FROM confluentinc/cp-kafka-connect:7.2.5

#If you want to run a local build of the connector, uncomment the COPY command and make sure the JAR file is in the directory path
#COPY mongo-kafka-connect-<<INSERT BUILD HERE>>3-all.jar /usr/share/confluent-hub-components
RUN mkdir -p /usr/share/confluent-hub-components && \
    cd /usr/share/confluent-hub-components && \
    curl --remote-name --location --silent \
       https://search.maven.org/remotecontent?filepath=org/mongodb/kafka/mongo-kafka-connect/1.6.1/mongo-kafka-connect-1.6.1-all.jar

RUN confluent-hub install --no-prompt --verbose mongodb/kafka-connect-mongodb:latest

ENV CONNECT_PLUGIN_PATH="/usr/share/java,/usr/share/confluent-hub-components"

