- (optional) add `@Async` annotation to make component/service async 
    - use for notification push
    - (act as middleware event bus) use for sending to another async component/service : send event --> event consume by @Async component --> send to other downstream
    - https://www.youtube.com/watch?v=LCGr7-ZgiIA
    
- pattern : run in a `while loop / cron pattern / scheduler` : https://www.youtube.com/watch?v=gH99_aiNUfY&list=PLZdfbI_OZWAPZAMWYv9NVBEGmqNZM-k0R&index=8
  - create a component/service, and it will run every 10min
  - (optional) ^ above component/service will read data from global static list and process
  - m1: any service an update the global field
  - m2: @Async service can update that global field (async coz it will validate event msg and send to different global static fields (different channel fields for different types))

    -  compare object value:  `.filter(u -> Objects.equals(u.getId(), id))` 
    - `final Map<String, Object> innerRequestMap = Objects.requireNonNullElseGet(reqMap, HashMap::new);`
    - combine 2 list : `List<String> newList = Stream.concat(listOne.stream(), listTwo.stream()).collect(Collectors.toList());` 
    - type cast : Student.class.cast(obj1)