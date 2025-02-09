# AI를 통한 TODO Stat산정하기 
- Todo 항목 작성시 AI를 이용해 스탯 산정을 해준다. <br>
- ex) 1. 유산소 운동 1시간 하기 -> 근지구력+3, 지구력+2, 끈기+2 <br>


# 기술 스택
Java/SpringBoot/SpringAI/SpringJPA/React/MariaDB/MongoDB <br>

# 기술 구성
BackEnd - Java/SpringBoot 를 통한 REST API 구성 <br>
AI - SpringAI를 사용해 프로토타입 작성(OpenAI API 사용) , 추후 LLM 파인튜닝을 통해 LANGCHAIN 과 FASTAPI로 전환 <br>
FrontEnd - React를 통해 웹을 구성, React로 완료 되면 ReactNative를 통해 모바일 어플까지 생각중 <br>
DB - MariaDB - 유저 관련 데이터 / MongoDB - AI관련 처리 데이터


# 코드 작성 규칙( 생각나는 대로 추가예정)

1. 업데이트 정보는 모두 DTO 내부에 숨긴다.
- ex) userId가 필요한 경우 해당 DTO에 userId를 추가한다.

2. serviceLayer에서는 Optional 반환을 원칙으로 한다. controller에서는 Optional을 반환받아 처리한다. 
