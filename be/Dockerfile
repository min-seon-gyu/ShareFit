FROM openjdk:17-jdk-slim
ENV JAVA_OPTS="$JAVA_OPTS \
	-javaagent:/home/ubuntu/pinpoint-agent-2.2.3-NCP-RC1/pinpoint-bootstrap-2.2.3.jar \
    -Dpinpoint.applicationName=shareFit \
    -Dpinpoint.agentId=gcael"
COPY build/libs/*SNAPSHOT.jar /sharefit_server.jar
ENTRYPOINT ["java", "-jar", "/sharefit_server.jar"]