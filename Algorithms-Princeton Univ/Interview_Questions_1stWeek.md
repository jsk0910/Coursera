### Interview Questions on 1st Week  
  
this document is for Interview Questions on Coursera - Algorithms (Princeton Univ.) Part I.  
Because this task goes through similarity verification, copying can cause problems.  
-> 이 문서는 Coursera - Alogrithms Part I (Princeton Univ.)의 1주차 Interview Questions에 대한 제 답을 저장한 것입니다.  
해당 과제는 유사도 검증을 거치기 때문에 그대로 복사할 시 문제가 발생할 수 있습니다.  
-> 모든 문제, 자료의 출처는 Princeton University와 Coursera에 있습니다.  

> #### 1. Union-Find (합집합 찾기)
> > 1. __Social network connectivity.__ : Given a social network containing $n$ members and a log file containing $m$ timestamps at which times pairs of members formed friendships, design an algorithm to determine the earliest time at which all members are connected(i.e., every member is a friend of a friend of a friend... of a friend). Assume that the log file is sorted by timestamp and that friendship is an equivalence relation. The running time of your algorithm should be $mlogn$ or better and use extra space proportional to $n$.
> > -> 소셜 네트워크 연결 : $n$ 명의 멤버가 연결된 소셜 네트워크와 멤버 간의 연결된 시간(팔로우 등) $m$ 개의 타임스탬프가 포함된 로그 파일이 주어질 때, 모든 구성원이 연결되는 가장 빠른 시간을 결정하는 알고리즘을 고안하시오.(모든 멤버는 친구의 친구 형식으로 연결된다.)

> #### 2. Analysis of Algorithms (알고리즘 분석)  
>  > 1. __3-SUM in quadratic time.__ : Design an algorithm for the 3-SUM problem that takes time proportional to $n^2$ in the worst case. You may assume that you can sort the $n$ integers in time proportional to $n^2$ or better.  
>  > -> 3-SUM 이차 시간 : 가장 안 좋은 케이스에서 $n^2$ 시간 복잡도가 나오는 3-SUM 알고리즘을 만드세요. $n$개 이상의 정수를 $n^2$ 혹은 더 빠른 시간에 비례하여 정렬할 수 있다고 가정할 수 있습니다.  
>  > + 풀이 아이디어 : HashMap을 활용하면 $O(n)$ 시간 복잡도로 해결이 가능합니다. 하지만, 이 강의에서 원하는 방법은 아닐 것이라고 생각되어 HashMap을 활용하지 않는 알고리즘을 구현하는 것이 좋습니다.  
>  > + 풀이 방법 : 먼저 주어진 배열을 정렬합니다. 그 다음 sum 값이 들어갈 변수를 S라 할 때, 배열의 각 요소에 대해서 W = S - i로 정의합니다.  
>  >               그 다음 배열의 맨 앞에서부터 시작해서 두 요소의 합이 W 보다 크다면 인덱스를 하나 줄이고(index - 1), 아니라면 인덱스를 하나 올립니다(index + 1).  
>  >               sum 값이 W와 같으면 반복문을 종료합니다.  
>  > + 시간 복잡도 : 배열을 정렬할 때 종류에 따라 다르겠지만 $O(nlogn)$ 혹은 $O(n^2)$의 시간이 걸립니다. 또, for문이 하나 밖에 없기 때문에 최악의 상황이라도 n번 돌아가 $(O(n))$이 나오고, 이를 합쳤을 때 가장 최악의 경우라고 하더라도 $O(n^2) + O(n) ~ O(n^2)$이 나오게 됩니다.  
>  > + 제가 쓴 정답 : First, Sort the array. Let the sum be S, for each element i in the array, let W = S - i. Start from the begin index and end index of array, if sum of two elements > W, then end index - 1, else then begin index + 1, Stop when the sum = W.  
>  > + 실제 정답  
>  > ![image](https://user-images.githubusercontent.com/23286838/224547432-d9fc4b97-c615-4d95-8841-09402e88c506.png)  
>  > 2. __Search in a bitonic array.__ : an array is _bitonic_ if it is comprised of an increasing sequence of intergers followed immediately by a decreasing sequence of integers. Write a program that, given a bitonic array of n distinct integer values, determines whether a given integer is in the array.  
>  >                                     + Standard version : Use ~ $3lgn$ compares in the worst case.  
>  >                                     + Signing bonus : Use ~ $2 lgn$ compares in the worst case.(and prove that no algorithm can guarantee to perform fewer than ~ $2lgn$ compares in the worst case).  
>  > -> bitonic 배열에서 탐색 : bitonic 배열이 주어졌을 때, 탐색하고자 하는 정수가 이 배열에 있는 지 없는 지를 알려주는 프로그램을 작성하시오.  
>  > + bitonic array? : 이 배열은 값이 증가했다가 감소했다가 혹은 감소했다가 증가하는 수열을 의미합니다.  
>  > + 풀이 아이디어 : 바이너리 서치를 이용하는 것이 가장 간단하게 풀 수 있을 것이라고 생각했습니다. 그래서 맨 앞부터 끝까지 이진 탐색을 하고 두 부분으로 나눠서 탐색을 시도합니다. 이것을 i가 나타날 때까지 반복합니다.  
>  > + 풀이 방법  
>  > > Use binary find, let i denote the element to find  
>  > > if i < target then  
>  > > &emsp;if left bound of interval < i then,    
>  > > &emsp;&emsp;search in left of array  
>  > > &emsp;if right bound of interval < i then,  
>  > > &emsp;&emsp;search in right of array  
>  > > else if i > target then,  
>  > > &emsp;if target is in increasing trend then  
>  > > &emsp;&emsp;search in right of array  
>  > > &emsp;else then  
>  > > &emsp;&emsp;search in left of array  
>  >   이렇게 변형된 이진 탐색을 만들어서 풀면 됩니다.  
>  > + 실제 정답  
>  > ![image](https://user-images.githubusercontent.com/23286838/224547379-6def50ea-a53b-43dd-9b8f-39e0ff256a7a.png)  
>  > 3. __Egg drop.__ : Suppose that you have an $n$-story building (with floors 1 through $n$) and plenty of eggs. An egg breaks if it dropped from floor $T$ or higher and does not break otherwise. Your goal is to devise a strategy to determine the value of $T$ given the following limitations on the numbers of eggs and tosses.  
>  > -> 에그 드랍 : 1층부터 n층까지의 빌딩과 많은 달걀이 있다고 가정한다. T층 이상에서 떨어질 때 달걀은 깨진다. 그렇지 않으면 깨지지 않는다. 목표는 T의 값을 결정하는 전략을 고안하는 것이다. (단, 계란과 던지는 횟수의 숫자에 제한이 있다.)  
>  > + 제한 사항  
>  > &emsp; * Version 0 : 1 egg, $\le T$ tosses.  
>  > &emsp; * Version 1 : ~ $1lgn$ eggs and ~ $1lgn$ tosses.  
>  > &emsp; * Version 2 : ~ $lgT$ eggs and ~ $2lgT$ tosses.  
>  > &emsp; * Version 3 : 2 eggs and ~ $2 \sqrt n$ tosses.  
>  > &emsp; * Version 4 : 2 eggs and $\le c\sqrt T$ tosses for some fixed constant c (c는 상수)  
>  > + 풀이 : For the version 0, a simple iterative search, starting from 1st floor to Nth floor in increments of 1.  
>  >          For the version 1, a binary search across 1st to Nth.  
>  >          For the version 2, starting from 1st floor, and go to 2nd floor, then go to 2^k floor. Then binary search across 2^k and 2^k-1.  
>  >          For the version 3, go iteratively go across floor with incrementing by sqrt N like o to sqrt N to 2 * sqrt N etc. Once the egg breaks at stage k * sqrt N, iterate across the range (k-1) * sqrt N and k * sqrt N one floor at a time.  
>  >          For the version 4, Go to first drop floor with version 3's step. Then backtracking from their.  
>  > + 실제 정답  
>  > ![image](https://user-images.githubusercontent.com/23286838/224547338-26574ecd-29e9-4057-aaf3-cbc6d70cb17b.png)  

