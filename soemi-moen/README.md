sudo docker run -d --name camunda -p 8080:8080 camunda/camunda-bpm-platform:run-latest --net=host
-v /home/flurin/soemi-weather/soemi-moen/target:/camunda/internal/webapps
