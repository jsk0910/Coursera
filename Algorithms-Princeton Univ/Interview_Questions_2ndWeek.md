### Interview Questions on 2nd Week  
  
This document is for Interview Questions on Coursera - Algorithms Part I (Princeton Univ.).  
Because this task goes through similarity verification, copying can cause problems.  
copyright of every reference is from Princeton University and Coursera.  
-> 모든 문제와 자료의 출처는 Princeton University와 Coursera에 있습니다.  
  
> #### 1. Stacks and Queues.  
> > 1. __Queue with two stacks.__ : Implement a queue with two stacks so that each queue operations takes a constant amortized number of stack operations.  
> > -> 두 개의 스택과 큐 : 각각의 큐 명령이 일정하게 분리된 스택 명령어를 가지도록 두 개의 스택으로 큐를 구현하라(제대로 이해한 것인지 모르겠습니다)  
> > + 풀이 아이디어 : 두 개의 스택으로 큐를 만들려면 한 쪽은 들어오는 데이터(enqueue)를 담당하고 한 쪽은 나가는 데이터(dequeue)를 담당하도록 해야할 것 같다고 생각했습니다.
> > + 제가 쓴 정답  
> > ```
> >   enqueue : push to stack1  
> >   dequeue :  
> >   if stack2 is null then  
> >     p = pop(stack1)  
> >     push p to stack2  
> >   else then  
> >     pop(stack2)  
> > ```
> > + 실제 정답
> > 2. __Stack with Max.__ : Create a data structure that efficiently supports the stack operations (push and pop) and also a return-the-maximum operation. Assume the elements are real numbers so that you can compare them.  
> > -> 최댓값과 스택 : 스택 명령어 (push와 pop)를 효과적으로 지원하고 최댓값을 반환하는 명령어가 있는 자료구조를 만들어라. 요소는 실수이고, 서로 비교할 수 있다.  
> > + 풀이 아이디어 : 스택을 만들고 max 명령어를 실행하면 최댓값을 찾아 반환하도록 할 수 있겠으나, 비효율적이지 않을까하고 생각했다. 그래서 노드(링크드 리스트) 형태의 스택을 만들고 push 명령어를 할 때마다 기존의 최댓값과 비교하도록 하는 것이 좋아보였다. 
> > + 제가 쓴 정답
> > ```
> >   First, Make node for max. This node's default value is null.
> >   When New data comes in(push), call the compareMax function. In that function, change the max node to the new max value.  
> > ```
> > + 실제 정답
> > + 풀이할 때 만든 코드
> > ``` java
> > public class MaxStack {
> >   private Node top; // stack의 제일 위에 있는 노드
> >   private int size;
> >   private float max; // 첫번째 방법을 위한 변수
> >   private Node _max; // 두번째 방법을 위한 변수
> >   
> >   public MaxStack() { // 생성자 함수
> >     size = 0;
> >     top = null;
> >     _max = null;
> >   }
> >   
> > ```
