curl -w "\n" -H "Content-Type: multipart/form-data" -X POST \
  -F "deployment-name=soemi-moen" \
  -F "enable-duplicate-filtering=true" \
  -F "deploy-changed-only=false" \
  -F "data=@/home/flurin/soemi-weather/soemi-moen/soemi-moen.bpmn" \
  http://xn--smi-weather-rfb.ch/moenengine-rest/deployment/create

