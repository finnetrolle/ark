# Ark

Ark is a simple tool helping you to move messages between RabbitMQ queues.

## Commands
  - copy from queue A to queue B
  - send message to queue
  
### Copy

To copy messages from one queue to another execute this command:
```sh
java -jar ark copy from_queue_name to_queue_name
```
All of your messages will be moved

### Send

To send message "Hello" to queue "some_queue" use this command:
```sh
java -jar ark send some_queue Hello
```
You can only send string-based messages, so this is not very useful command

## Connection setings

By default ark is going to execute your command on a localhost RabbitMQ server using default username and password.
If you want to change hostname, username or password - use options -h, -u or -p with your values. 
Let's copy some queues on remote server for example:
```sh
java -jar ark copy q.one q.two -h www.rabbit-server.com -u root -p 12345
```
Ark will connect to www.rabbit-server.com with username = root and password = 12345 and copy all messages from q.one to q.two