- factory pattern 
    - spring boot simple impl m1: https://youtu.be/gd3Pg0fAcjI ,  https://github.com/hocyadav/FactoryDesignPattern
    - spring boot simple impl m2 (less code): https://youtu.be/K_-w8uaFaYk , https://github.com/hocyadav/FactoryDesignPattern-Ex-2 
    - spring boot simple impl m1-m2 : https://youtu.be/lKFD75zaWxk
- strategy pattern 
    - using spring boot lib (by josh long): https://youtu.be/GlV5sXdXPu4
  
- publish-subscribe pattern / event bus / message bus / interface
  - A Message Bus is a messaging infrastructure to allow different systems to communicate through a shared set of `interfaces(message bus)`
  - A message bus facilitates events being routed between microservices; without having tight coupling between microservices.
  - The event bus uses a publish-subscribe pattern. It allows consumers to subscribe to messages of certain types and allows producers to create messages of a certain type. The bus then connects these producers and consumers together.
    - https://medium.com/usertesting-engineering/event-based-microservices-message-bus-5b4157d5a35d
    - https://www.google.com/search?q=message+bus+implementation&rlz=1C5CHFA_enIN956IN956&sourceid=chrome&ie=UTF-8
    - https://medium.com/usertesting-engineering/event-based-microservices/home
  - Simple impl
      - sender service `event/msg` --1:1-> [message bus interface] --1:m-> consumer service
      - message bus can convert `1 event` into `many events` and send to `different queues` and from different queues different consumer service pick event and process

