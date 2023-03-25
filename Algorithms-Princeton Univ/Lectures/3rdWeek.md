# 3rd Week Lectures  
  
This document summarizes 3rd Week lectures.  
The copyright is at Princeton University and Coursera.  

이 문서는 3주차 강의를 요약, 정리한 것입니다. 모든 저작권은 프린스턴 대학교와 Coursera에 있습니다.  

***  
### MergeSort  
3주차 1차시 - MergeSort  
#### 0. Overview  
이번 주차의 강의는 약 50년 전 개발됐지만 사실상 모든 소프트웨어 시스템과 연구에 사용되는 여전히 중요하고 의미가 있는 두 가지 클래식한 알고리즘에 기반합니다. 이번 강의에서는 이런 방법이 효율적인 이유를 설명하는 수학적 모델부터 현대 시스템의 실제 응용 프로그램에 적용하는 세부사항을 다룹니다.  
__Lecture : MergeSort__ : 1차시 강의인 병합정렬에서는 병합정렬 알고리즘을 공부하고 이것이 거의 $nlgn$ 번의 비교로 $n$개의 요소가 있는 모든 배열을 정렬할 수 있음을 보여줍니다.  
또한, 비재귀적인 bottom-up 버전을 고려합니다. 우리는 모든 비교 기반의 알고리즘들이 가장 최악의 경우 $nlgn$ 시간이 걸린다는 것을 증명합니다.  
__Lecture : QuickSort__ : 무작위 퀵 정렬을 소개하고 구현하며 이것의 성능을 분석합니다. 또한, k번째 가장 작은 요소를 선형시간 안에 찾는 변형된 퀵 정렬인 __무작위 퀵 선택__ 을 고려합니다.  
마지막으로, 중복된 키가 존재할 때 잘 작동하는 3-way quickort를 다룹니다.  
  
***  
#### 1. MergeSort  