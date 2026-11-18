QuickChat - POE Project
QuickChat is a Java console application that allows users to register, log in, send, store and manage messages.
Features
1. Registration & Login
•	Username must contain `_` and be 5 characters or less
•	Password must be at least 8 characters, with a capital letter, a number and a special character
•	Login with username and password

2. Send Messages
•	Choose how many messages to send
•	Enter recipient cell number (must start with `+27`)
•	Enter message (max 250 characters)
•	A unique 10-digit Message ID is generated
•	A Message Hash is created

For each message, the user can choose:
1.	Send Message
2.	Disregard Message
3.	Store Message to Send Later
3. Stored Messages

•	Stored messages are saved in `storedMessages.json`
•	They are loaded back on start-up with `Stored.loadFromJSON()`
•	User can display sender and recipient of stored messages
4. Reports

•	Show recently sent messages
•	Show longest message
•	Search by recipient or by Message ID
•	Delete message by hash

##Project Structure
•	`Main.java` - Main menu and program flow
•	`Login.java` - Registration, login and validation
•	`Message.java` - Message class, ID, hash, validation
•	`Stored.java` - Stored messages and JSON file handling
•	`Testing.java` - Unit tests

How To Run
1.	Open project in IntelliJ IDEA
2.	Make sure all files are in `src/`
3.	Run `Main.java`
4.	Follow the menu:

## Stored 
Stored messages are saved in 'storedMessages.json' :

##Testing
Tests are in `Testing.java` and cover:
•	Username validation
•	Password validation
•	Login
•	Message length
•	Cell number validation
•	Message hash and ID creation
Requirements
•	Java 17 or later
•	IntelliJ IDEA
•	No external libraries needed

##Author
[LUBO CONSOLATTE .K]
PROG POE - QuickChat


