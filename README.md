# README

This is a web application for the user study in Final Year Project —— "Deep-colorization". 100 true colorful images with three different colorization approaches applied, there are, overall, 400 images in the library. 

To run this in docker, simply type in these three command:

`docker network create -d bridge survey`

`docker run -p 27018:27017 --name db --network survey -d mongo:latest`

`docker run -p 8081:8080 --name web --network survey -d tonyzhang1002/fyp-survey:web`

After that, service can be found on `localhost:8081/surveyForm`