# Flood Detector
has three apps : goverment and citizen with an adapter app 
Documentation : 
  For documentation please look for documentation in the repo

# Documentation
Flood detection and prevention
Index
Contents
Aim ……………………………………………………………………………………………2
Technologies used……………………………………………………………………..2
Working
IBM Cloud…………………………………………………………………………2
Sensor Technology……………………………………………………………3
Firebase cloud………………………………………………………………....4
Other problems covered…………………………………………………………..4
Block diagrams………………………………………………………………………….5
Code…………………………………………………………………………………………5
Screeenshots……………………………………………………………………………16
Conclusion……………………………………………………………………………….22
Links…………………………………………………………………………………………22

# Flood detection and prevention
Aim : To detect floods and prevent the major damage created by floods
Technologies used :
IBM cloud
Google Firebase cloud
Sensor Technology
Machine learning algorithms
Working :
Date from sensor technology are taken in order to predict the climatic
changes for flood detection, the values taken are
->Temperature
-> Humidity
->Water-flow rate
->Level of water
Temperature is the temperature of the climate at present condditions
Humidity is present humidity in the cloud
Water-flow rate is the flow rate of water in the drainages
Level of water is water level in the dams
Thus all the values collected by the sensors are given to the Nodemcu chip which
is connected to IBM cloud for detection and processing of data


# The chip technology used are
->Temperature : DHT11
-> Humidity : DHT11
->Water-flow rate : YF-S201
-> Level of water : Ultrasonic sensor
The values thus obtained are forwarded to IBM cloud
In IBM cloud data is thus processes using Machine learning algorithms
The Algorithm used in cloud are
->Random Forest
The data thus obtained was send to the firebase cloud by using adapter app
Thus data uploaded to cloud is accessed through android apps i.e apps are
->Government app
->Citizen app
The Government app has the data required for government servant
It contains
->Location of defect
->Next safe location
The citizen app contains data
->Location of defect
->Next safe location
->Location of Government citizens
->Contact details of Government citizens
Since data comes from firebase data base the changes done is quick in the app

# Other problems covered :
There is no technology up to now which specifies the
drainage flow , so this give a solution to keep drain flow stable with out stagnating
