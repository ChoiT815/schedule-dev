📅 일정 관리 API 명세 (Schedule API)
본 문서는 Schedule & User API 명세서입니다.

공통 사항

- **Schedule Base URL**: /schedules
- **User Base URL**: /users

Auditing
createdAt (작성일) - JPA Auditing 자동 처리
updatedAt (수정일) - JPA Auditing 자동 처리




🗂️ ERD (Entity Relationship Diagram)
#mermaid-r2l4{font-family:inherit;font-size:16px;fill:#E5E5E5;}@keyframes edge-animation-frame{from{stroke-dashoffset:0;}}@keyframes dash{to{stroke-dashoffset:0;}}#mermaid-r2l4 .edge-animation-slow{stroke-dasharray:9,5!important;stroke-dashoffset:900;animation:dash 50s linear infinite;stroke-linecap:round;}#mermaid-r2l4 .edge-animation-fast{stroke-dasharray:9,5!important;stroke-dashoffset:900;animation:dash 20s linear infinite;stroke-linecap:round;}#mermaid-r2l4 .error-icon{fill:#CC785C;}#mermaid-r2l4 .error-text{fill:#3387a3;stroke:#3387a3;}#mermaid-r2l4 .edge-thickness-normal{stroke-width:1px;}#mermaid-r2l4 .edge-thickness-thick{stroke-width:3.5px;}#mermaid-r2l4 .edge-pattern-solid{stroke-dasharray:0;}#mermaid-r2l4 .edge-thickness-invisible{stroke-width:0;fill:none;}#mermaid-r2l4 .edge-pattern-dashed{stroke-dasharray:3;}#mermaid-r2l4 .edge-pattern-dotted{stroke-dasharray:2;}#mermaid-r2l4 .marker{fill:#A1A1A1;stroke:#A1A1A1;}#mermaid-r2l4 .marker.cross{stroke:#A1A1A1;}#mermaid-r2l4 svg{font-family:inherit;font-size:16px;}#mermaid-r2l4 p{margin:0;}#mermaid-r2l4 .entityBox{fill:transparent;stroke:#A1A1A1;}#mermaid-r2l4 .relationshipLabelBox{fill:#CC785C;opacity:0.7;background-color:#CC785C;}#mermaid-r2l4 .relationshipLabelBox rect{opacity:0.5;}#mermaid-r2l4 .labelBkg{background-color:rgba(204, 120, 92, 0.5);}#mermaid-r2l4 .edgeLabel .label{fill:#A1A1A1;font-size:14px;}#mermaid-r2l4 .label{font-family:inherit;color:#E5E5E5;}#mermaid-r2l4 .edge-pattern-dashed{stroke-dasharray:8,8;}#mermaid-r2l4 .node rect,#mermaid-r2l4 .node circle,#mermaid-r2l4 .node ellipse,#mermaid-r2l4 .node polygon{fill:transparent;stroke:#A1A1A1;stroke-width:1px;}#mermaid-r2l4 .relationshipLine{stroke:#A1A1A1;stroke-width:1;fill:none;}#mermaid-r2l4 .marker{fill:none!important;stroke:#A1A1A1!important;stroke-width:1;}#mermaid-r2l4 :root{--mermaid-font-family:inherit;}1:NUSERLONGidPKSTRINGusernameSTRINGemailDATETIMEcreated_atDATETIMEupdated_atSCHEDULELONGidPKLONGuser_idFKSTRINGtitleSTRINGcontentDATETIMEcreated_atDATETIMEupdated_at

👤 User API
User 공통 필드
필드명타입설명idLong유저 IDusernameString유저명emailString이메일createdAtLocalDateTime작성일 (자동 생성)updatedAtLocalDateTime수정일 (자동 갱신)

1. 유저 생성 API
   POST /users
   Request Body
   json{
   "username": "godi5",
   "email": "godi5@email.com"
   }
   Response (201 Created)
   json{
   "id": 1,
   "username": "godi5",
   "email": "godi5@email.com",
   "createdAt": "2026-04-17T10:30:00",
   "updatedAt": "2026-04-17T10:30:00"
   }

2. 유저 전체 조회 API
   GET /users
   Response (200 OK)
   json[
   {
   "id": 1,
   "username": "godi5",
   "email": "godi5@email.com",
   "createdAt": "2026-04-17T10:30:00",
   "updatedAt": "2026-04-17T10:30:00"
   }
   ]

3. 유저 단건 조회 API
   GET /users/{id}
   Response (200 OK)
   json{
   "id": 1,
   "username": "godi5",
   "email": "godi5@email.com",
   "createdAt": "2026-04-17T10:30:00",
   "updatedAt": "2026-04-17T10:30:00"
   }

4. 유저 수정 API
   PATCH /users/{id}
   Request Body
   json{
   "username": "godi5_수정",
   "email": "godi5_new@email.com"
   }
   Response (200 OK)
   json{
   "id": 1,
   "username": "godi5_수정",
   "email": "godi5_new@email.com",
   "createdAt": "2026-04-17T10:30:00",
   "updatedAt": "2026-04-17T10:45:00"
   }

5. 유저 삭제 API
   DELETE /users/{id}
   Response (200 OK)
   json{
   "id": 1,
   "username": "godi5_수정",
   "email": "godi5_new@email.com",
   "createdAt": "2026-04-17T10:30:00",
   "updatedAt": "2026-04-17T10:45:00"
   }

📅 Schedule API
Schedule 공통 필드
필드명타입설명idLong일정 IDuserIdLong유저 ID (FK)titleString할일 제목contentString할일 내용createdAtLocalDateTime작성일 (자동 생성)updatedAtLocalDateTime수정일 (자동 갱신)

1. 일정 생성 API
   POST /schedules
   Request Body
   json{
   "userId": 1,
   "title": "일정 생성",
   "content": "schedule API 만들기"
   }
   Response (201 Created)
   json{
   "id": 1,
   "userId": 1,
   "title": "일정 생성",
   "content": "schedule API 만들기",
   "createdAt": "2026-04-17T10:30:00",
   "updatedAt": "2026-04-17T10:30:00"
   }

2. 일정 전체 조회 API
   GET /schedules
   Response (200 OK)
   json[
   {
   "id": 1,
   "userId": 1,
   "title": "일정 생성",
   "content": "schedule API 만들기",
   "createdAt": "2026-04-17T10:30:00",
   "updatedAt": "2026-04-17T10:30:00"
   }
   ]

3. 일정 단건 조회 API
   GET /schedules/{id}
   Response (200 OK)
   json{
   "id": 1,
   "userId": 1,
   "title": "일정 생성",
   "content": "schedule API 만들기",
   "createdAt": "2026-04-17T10:30:00",
   "updatedAt": "2026-04-17T10:40:00"
   }

4. 일정 수정 API
   PATCH /schedules/{id}
   Request Body
   json{
   "title": "일정 수정",
   "content": "CRUD 완성"
   }
   Response (200 OK)
   json{
   "id": 1,
   "userId": 1,
   "title": "일정 수정",
   "content": "CRUD 완성",
   "createdAt": "2026-04-17T10:30:00",
   "updatedAt": "2026-04-17T10:45:00"
   }

5. 일정 삭제 API
   DELETE /schedules/{id}
   Response (200 OK)
   json{
   "id": 1,
   "userId": 1,
   "title": "일정 수정",
   "content": "CRUD 완성",
   "createdAt": "2026-04-17T10:30:00",
   "updatedAt": "2026-04-17T10:45:00"
   }

##  Lv1 요구사항 충족 체크

- 일정 생성 / 조회 / 수정 / 삭제 (CRUD)
- JPA Auditing으로 작성일·수정일 자동 관리
- 비밀번호 등 민감 정보는 환경변수로 관리

## Lv2 요구사항 충족 체크

- 유저 생성 / 조회 / 수정 / 삭제 (CRUD)
- JPA Auditing으로 작성일·수정일 자동 관리
- 일정과 유저 연관관계 구현 (1:N)