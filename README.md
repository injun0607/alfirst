# AI를 통한 Stat 산정 프로젝트

# 기획 의도 
개인의 스탯을 만들어서 실시간 피드백을 주면 사용자가 행동을 지속하지 않을까?

# 아이디어
사용자의 계획 및 일정 리스트를 받아 , 행동을 AI로 분석해, 사용자가하는 행동을 스탯포인트로 치환해서 피드백을 준다.

# 주의해야하는 부분
- 개인마다 피드백 주기에 대해 신경써야함
- 스탯의 신뢰성을 유지시켜야 된다.(차별화 되지않은 일괄적인 향상은 올라가는 스탯에 대해 의문을 품을수 있음)<br>
  => 개인적인 LLM모델이 있어야 관리가 편할듯
- 스탯 피드백의 퀄리티 유지(기본적인 텍스트로만 제공되는 스탯 피드백은 자칫 흥미를 떨어트릴수 있다)

# 기술 스택
Java/SpringBoot/SpringAI/SpringJPA/React/MariaDB/MongoDB <br>

# 기술 구성
1. BackEnd - Java/SpringBoot 를 통한 REST API 구성 <br>
2. AI - SpringAI를 사용해 프로토타입 작성(OpenAI API 사용) , 추후 LLM 파인튜닝(LLAMA 3.2 - 3B 으로 테스트 도전중) 을 통해 LANGCHAIN 과 FASTAPI로 전환  <br>
3. FrontEnd - React를 통해 웹을 구성, React로 완료 되면 ReactNative를 통해 모바일 어플까지 생각중 <br>
4. DB - MariaDB - 유저 관련 데이터 / MongoDB - AI관련 처리 데이터


# 코드 작성 규칙( 생각나는 대로 추가예정)

1. 업데이트 정보는 모두 DTO 내부에 숨긴다.
- ex) userId가 필요한 경우 해당 DTO에 userId를 추가한다.

2. service에서는 Optional반환값을 처리해서 객체로 내려준다. 만약 값이 없는것에 대해선 객체마다 처리를 필요로 한다. service를 사용하는 레이어에선 객체만 받아 사용.
3. 비지니스 로직이 없는 메소드의 예외는 상위로 던진다. 해당 메소드를 사용하는 비지니스 로직에서 일괄처리해준다
- ex) userMapper에서 나오는 에러는 상위로 던지고 ,예외는 해당 메소드를 사용하는 service에서 처리한다.
4. 서비스로직에서 상세 에러 처리 , 컨트롤러 에러는 범용 에러로 처리


# 생각해봐야 할 것
1.  MariaDB와 MongoDB의 분산트랜잭션 문제 <br>
: 우선은 SAGA 패턴의 오케스트레이션으로 접근해보기. 근데 보상트랜잭션 문제는 Redis같은걸로 관리해야 하나.. 고민해봐야 할 듯
2. 혹시나 서비스가 대박이 나서 요청이 매우 많아져 AI처리가 따라오지 못해 요청이 밀리는 경우는 어떡하지? <br>
: kafaka또는 RabbitMQ를 이용해보면 좋을것 같은데 이건 추후 처리하면 될듯

