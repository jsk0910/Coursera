# 1st Week Lectures  
  
This document summarizes 3rd Week lectures.  
The copyright is at Princeton University and Coursera.  
  
이 문서는 3주차 강의를 요약, 정리한 것입니다. 모든 저작권은 프린스턴 대학교와 Coursera에 있습니다.  
***  
### Course Introduction  
1주차 1차시 - 강의 소개 
이 강의에서 다루는 주제는 다음과 같습니다.  
data types(데이터 타입) - stack(스택), queue(큐), bag(백), union-find(합집합 찾기), priority queue(우선순위 큐)  
sorting(정렬) - quicksort(퀵 정렬), mergesort(병합정렬), heapsort(힙정렬)  
searching(검색) - BST(이진트리), red-black BST(레드블랙 이진트리), hash table(해쉬 테이블)  
***  
### Union-Find  
1주차 2차시 - 합집합 찾기  
#### 0. Overview  
__Lecture : Union-Find__ : 동적인 연결 문제를 고려하면서 알고리즘을 개발하고 분석하는 우리의 기본적인 접근 방식을 설명합니다.  
_union-find_ 데이터 타입을 소개하고 다양하게 구현합니다.(quick find, quick union, weighted quick union 등)  
마지막으로, 물리, 화학의 여과 문제에 합집합 찾기 데이터 타입을 적용합니다.  
__Lecture : Analysis of Algorithms__ : 알고리즘의 성능을 분석하기 위한 접근의 기초는 과학적인 방법입니다. 프로그램 실행 시간을 측정하기 위한 컴퓨팅 실험을 수행하는 것으로 시작합니다.  
우리는 이러한 측정을 사용하여 성능에 대한 가설을 세웁니다. 그리고, 그것을 설명하기 위한 수학적 모델을 만듭니다. 마지막으로, 우리의 자바 프로그램의 메모리 사용량 분석을 고려합니다.  
#### 1. Dynamic Connectivity  
__동적 연결성__ : 동적 연결성 문제를 푸는 알고리즘으로 Quick Find, Quick Union을 살펴본다.  
> ##### 알고리즘을 개발하기 위해서 반복해야 하는 단계  
> 1. 문제 모델링  
> 문제의 주요 요소들이 무엇인지 기본적으로 이해해야한다. 그러면 문제를 풀 수 있는 몇가지 알고리즘을 떠올릴 수 있을 것이다.  
> 대부분의 경우에 처음 떠올린 알고리즘을 이용하면 문제를 풀 수 있다.  
> 다만, 어떤 경우에는 빠르지 않거나 메모리가 부족할 수 있다.  
> 2. 우리는 왜 속도가 느린지, 메모리가 부족한 지 이해하고 그 문제가 발생하는 원인을 찾고 새 알고리즘을 떠올려야 한다.  
> 이러한 방법을 알고리즘을 만들고 분석하는 _과학적 방법_ 이라고 한다.  

> ##### 동적 연결성 문제 : 이 문제는 union-find 문제에 대한 추상적인 모델이다.  
> N 개의 객체가 모여있다. 이 객체에 0부터 N-1까지 번호를 붙인다.  
> + __union__ 명령은 두 객체를 연결한다.  
> + __connected__ 명령은 두 객체 사이가 선으로 연결되어 있는지 확인한다.  
> 
> 이 문제는 두 객체를 연결하는 경로가 있는지 답하는 __connected__ 를 구현하는 것이다.  
> 중요한 점은 두 객체를 직접 연결하지 않더라도 간접적으로 연결되어 있으면 connected는 참이 된다는 것이다.  
> 그것을 해내는 것이 __union-find__ 알고리즘이다.  
> 이것을 이용하면 symbol-table이나 검색 알고리즘에도 적용이 가능하다.  
> 
> ##### 모델링
> 1. 반사적 : 모든 객체는 자기 자신에게 연결되어 있다.  
> 2. 대칭적 : P가 Q에 연결되어 있으면 Q도 P에 연결되어 있다.  
> 3. 이행적 : P가 Q에 연결되어 있고 Q가 R에 연결되어 있으면, P도 R에 연결되어 있습니다.  
> 
> 연결 요소(connected components)는 부분 집합으로 서로 연결된 요소들의 최대 집합이다.  
> ![image](https://user-images.githubusercontent.com/23286838/226327764-7b14a0bb-c2fa-43fd-a702-663b8b60df19.png)  
> 위 예에서 연결 요소는 {0}, {1, 4, 5}, {2, 3, 6, 7}이다.  
> 연결 요소를 유지함으로써 알고리즘이 효율성을 가진다.  
> 
> ##### 연산  
> query(connected)와 union을 구현해야 한다.  
> 1. find(connected된 것을 찾는 질의)는 동일한 연결 요소(집합)에 있는지를 체크하면 된다.  
> 2. union 명령은 두 객체를 포함하는 연결 요소들을 합집합으로 대체하면 된다.  
> 예를 들어서, union(2,5)라는 명령을 받으면 2를 포함한 연결 요소와 5를 포함한 연결 요소를 합쳐야 한다.  
>   
> ##### Union-Find Data Type  
> ```Java
> public class UF {
>   UF(int N) // UF 생성자 함수
>   void union(int p, int q) // p와 q 사이 연결
>   boolean connected(int, int q) // p와 q 사이가 연결되어 있는지 확인(즉, 같은 컴포넌트 안에 있는가?)
> ```
  
#### 2. Quick Find  
> 이 알고리즘은 열성 알고리즘이라고 불린다.  
> 이 알고리즘에서는 단순한 정수 배열로 된 객체를 자료구조로 사용된다.  
> 같은 id 즉, 같은 값을 가지고 있으면 서로 연결된 것이다.  
> 
