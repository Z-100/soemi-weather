sudo docker stop camunda
sudo docker remove camunda
sudo docker run -d --name camunda -p 8080:8080 --net=host camunda/camunda-bpm-platform:run-latest
sudo docker ps
