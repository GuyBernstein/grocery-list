# grocery list

sudo vi /etc/hosts
```
127.0.0.1 selenium-chrome
```
if you dont run in arm64 enviroment, try to change the image in docker-compose.yml from
- seleniarm/standalone-chromium:latest
to
- selenium/standalone-chromium:latest


### To test selenium controller you can: 
- in the url: https://www.google.com
- in the cssSelector: img[alt="Google"]
to see success
