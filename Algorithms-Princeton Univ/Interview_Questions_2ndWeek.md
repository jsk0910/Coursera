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
> > ![image](https://user-images.githubusercontent.com/23286838/226097317-4ac512ce-332c-4ddb-970d-a39156b59101.png)  
> > 
> > 2. __Stack with Max.__ : Create a data structure that efficiently supports the stack operations (push and pop) and also a return-the-maximum operation. Assume the elements are real numbers so that you can compare them.  
> > -> 최댓값과 스택 : 스택 명령어 (push와 pop)를 효과적으로 지원하고 최댓값을 반환하는 명령어가 있는 자료구조를 만들어라. 요소는 실수이고, 서로 비교할 수 있다.  
> > + 풀이 아이디어 : 스택을 만들고 max 명령어를 실행하면 최댓값을 찾아 반환하도록 할 수 있겠으나, 비효율적이지 않을까하고 생각했다. 그래서 노드(링크드 리스트) 형태의 스택을 만들고 push 명령어를 할 때마다 기존의 최댓값과 비교하도록 하는 것이 좋아보였다. 
> > + 제가 쓴 정답
> > ```
> >   First, Make node for max. This node's default value is null.
> >   When New data comes in(push), call the compareMax function. In that function, change the max node to the new max value.  
> > ```
> > 
> > + 풀이할 때 만든 코드
> > ``` java
> > public class MaxStack {
> >   private Node top; // stack의 제일 위에 있는 노드
> >   private int size;
> >   private float max; // 첫번째 방법을 위한 변수
> >   private Node _max; // 두번째 방법을 위한 변수
> >   
> >   private class Node {
> >     private float item;
> >     private Node next;
> >     private Node prev;
> >   }
> >   
> >   public MaxStack() { // 생성자 함수
> >     size = 0;
> >     top = null;
> >     _max = null;
> >   }
> >   
> >   public void push(float item) { // push 함수
> >     Node oldTop = top; // 원래 맨 위에 있는 노드(제일 마지막으로 들어온 노드)
> >     top = new Node(); // 맨 위에 새로운 노드 삽입
> >     top.item = item; // 데이터 추가
> >     
> >     if (oldTop != null) { // 원래 데이터가 있는 경우(탑이 있는 경우)
> >       top.next = oldTop;
> >       top.next.prev = top;
> >       if (_max.item < item) { // 새로 들어온 데이터가 기존의 최댓값보다 큰 경우
> >         _max = top; // 새로 들어온 데이터로 바꾸기
> >       }
> >     } else {
> >       _max = top; // 최댓값은 첫 데이터로 설정
> >     }
> >     size += 1;
> >   }
> > }
> > ```  
> > + 실제 정답
> > ![image](https://user-images.githubusercontent.com/23286838/226097352-6adfa277-eeac-48e2-af29-697970a32925.png)  
> > -> 두 개의 스택을 이용하는 방법을 쓰라고 하네요.  그래서 추가로 만들었습니다.
> >   
> > 3. __Java generics.__ : Explain why java prohibits generic array creation.  
> > -> 자바가 제너릭 배열 생성을 금지하는 이유를 설명하시오.  
> > + 제가 쓴 정답  
> > ```
> > array can change type to sub class type, this feature can cause exceptions like runtime error. It lowers the type stability.
> > So, Java Language was designed to contain only specified type in generic.
> > ```
> > + 실제 정답
> > ![image](https://user-images.githubusercontent.com/23286838/226097373-957195ec-432c-4796-bbf2-c34fa6d16206.png)  
***  
> #### 2. Elementary Sort  
> > 1. __Intersection of two sets.__ : Given two arrays $a[]$ and $b[]$, each containing n distinct 2D points in the plane, design a subquadratic algorithm to count the number of points that are contained both in array $a[]$ and array $b[]$.  
> > -> 두 집합의 교집합 : $a[]$와 $b[]$ 라는 두 개의 배열이 주어졌을 때(평면에서 각각 n개의 뚜렷한 2D 점을 포함합니다.), 두 배열에 모두 포함된 점의 숫자를 세는 subquadratic 알고리즘을 고안하시오.  
> > + 제가 쓴 정답  
> > ```
> > sort and count
> > ```
> > + 실제 정답
> > 
> > 2. __Permutation.__ : Given two integer arrays of size $n$, design a subquadratic algorithm to determine whether one is a permutation of the other. That is, do they contain exactly the same entries but, possibly, in a different order.  
> > -> 사이즈가 $n$인 두 개의 정수 배열이 주어질 때, 하나의 배열이 다른 하나의 순열인지를 결정하는 알고리즘을 고안하라. 즉, 항목이 동일하지만, 다른 순서로 되어있는지를 찾습니다.
> > + 제가 쓴 정답  
> > ```
> > sort both array and compare them.
> > ```
> > + 실제 정답 
> > 
> > 3. __Dutch national flag.__ : Given an array of $n$ buckets, each containing red, white, or blue pebble, sort them by color.  
> > ![image](https://user-images.githubusercontent.com/23286838/226100310-e1d89723-f268-4637-8540-e7b5ef333b32.png)  
> > -> 네덜란드 국기 : 빨간색, 하얀색, 또는 파란색 자갈돌이 들어있는 $n$ 개의 버킷의 배열이 주어졌을 때, 색을 기준으로 정렬하라.  
