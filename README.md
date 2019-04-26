# CollaborativeEditor
Centralized server for a real time collaborative Editor over a TCP socket

Group Number 2
Group Members Info:
1. Akshay Sehgal - sehgal-akshay
2. Kumar Kunal - kunal4892
3. Harshit Agrawal - harshitagrawal91

Everyone participated in the discussions and after finalising the architecture Harshit worked on the Server side developing
server side code and features. Akshay and Kumar worked to develop the client side and the UI part of the application. The 
integration is done by all three members working together on different systems and the same was tested and the bugs were
fixed as part of collaborative effort.

Brief Description of the Project
The project is an application which tried to emulate the functionality of the Collaborative Text Editor using the CRDT
algorithm and concurrent programming principles. The boundary cases like entering at start and end are handled. It uses the
server client architecture and once the server is up. There can be multiple clients accessing a document and performing insert,
update and delete. The same gets reflected in each copy and the final copy of all the documents converge.

Instructions to Build and Run the application

1. Run the Server project CollaborativeEditorServer.
It takes the port from serverInfo.txt(port- 7001)


2. Run the clients CollaborativeEditor with command line arguments port and IP Address
[7001 "localhost"]
