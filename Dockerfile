#De la imagen que se parte
FROM java:8-jre-alpine
#Copiar el proyecto en el directorio de trabajo
COPY target/API_rest-0.0.1.jar /api_rest
#Directorio de trabajo
WORKDIR /api_rest
#Exponer el puerto 8080
EXPOSE 8080
#Comando que se ejecutará una vez lanzado el contendor
CMD ["java","-jar","API_rest-0.0.1.jar"]
