# alfirst
alham First Project


# 코드 작성 규칙

1. 업데이트 정보는 모두 DTO 내부에 숨긴다.
- ex) userId가 필요한 경우 해당 DTO에 userId를 추가한다.

2. serviceLayer에서는 Optional 반환을 원칙으로 한다. controller에서는 Optional을 반환받아 처리한다. 
