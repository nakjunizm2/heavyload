FROM public.ecr.aws/amazoncorretto/amazoncorretto:17.0.9

ENV MANAGEMENT_ENDPOINTS_WEB_EXPOSURE_INCLUDE "health,metrics"
ENV MANAGEMENT_HEALTH_PROBES_ENABLED "true"
ENV MANAGEMENT_SERVER_PORT "8081"
ENV SERVER_SHUTDOWN "graceful"
ENV JDK_JAVA_OPTIONS "--illegal-access=warn --add-opens java.base/java.lang=ALL-UNNAMED -XX:MaxRAMPercentage=75"
ENV SPRING_MAIN_KEEPALIVE "true"
#ENV SPRING_THREADS_VIRTUAL_ENABLED "true"

RUN echo "networkaddress.cache.ttl=10" >> ${JAVA_HOME}/conf/security/java.security
RUN echo "networkaddress.cache.negative.ttl=10" >> ${JAVA_HOME}/conf/security/java.security

EXPOSE 8080

ARG JAR_FILENAME="*.jar"
COPY $JAR_FILENAME /app.jar

CMD ["java", "-XX:+PrintFlagsFinal", "-Dserver.port=8080", "-jar", "/app.jar"]

HEALTHCHECK CMD curl -f http://127.0.0.1:$MANAGEMENT_SERVER_PORT/actuator/health || exit 1
