- goal : even if we get error, our execution should not stop
---
- `errors` : logical error compile time, run time
    - normal steps
    - critical steps (wrap over try catch) , try means try to execute that critical statement 😄 it may work/not work
  
- user don't understand `exceptions` so send user friendly message
- whenever we `open resources` (as normal step/critical steps), `always close` it e.g., when u open a fridge and in between someone calls u then what u do 😄
- handle different type of exception like, 
  - case 1: input type will give different exception 
  - case 2: different exception type in different catch block 
  - case 3: ... 
  - case 4: generic exception handle 

- time out : for external http client call, make sure it will not block for infinite time, so add timeout in critical section + handle that exception type