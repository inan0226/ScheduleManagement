
#  Schedule Management API Specification

본 프로젝트는 일정 관리 시스템을 위한 백엔드 API 라이브러리입니다.

##  API Endpoints Summary

| 기능 | Method | URL | 설명 |
| :--- | :---: | :--- | :--- |
| **일정 생성** | `POST` | `/schedules` | 새로운 일정을 등록합니다. |
| **일정 조회** | `GET` | `/schedules` | 특정 작성자의 전체 일정을 조회합니다. |
| **일정 수정** | `PATCH` | `/schedules/{id}` | 특정 일자의 정보를 부분 수정합니다. |
| **일정 삭제** | `DELETE` | `/schedules/{id}` | 특정 일정을 삭제합니다. |

---

## 일정 생성 (Create Schedule)

**URL:** `POST /schedules`  
**Description:** 일정 정보를 입력받아 데이터베이스에 저장합니다.

###  요청 (Request)
* **Headers**
    | 이름 | 타입 | 필수 여부 | 설명 |
    | :--- | :--- | :---: | :--- |
    | Content-Type | String | Y | `application/json` |

* **Body** (`CreateScheduleRequest`)
    | 이름 | 데이터 타입 | 설명 |
    | :--- | :--- | :--- |
    | **name** | String | 작성자 이름 |
    | **title** | String | 일정 제목 |
    | **content** | String | 일정 상세 내용 |
    | **author** | String | 작성자 아이디/이메일 |
    | **password** | String | 일정 수정을 위한 비밀번호 |

###  응답 (Response)
* **성공 응답 (201 Created)**
    | 이름 | 데이터 타입 | 설명 |
    | :--- | :--- | :--- |
    | **id** | Long | 생성된 일정 고유 ID |
    | **name** | String | 작성자 이름 |
    | **title** | String | 일정 제목 |
    | **content** | String | 일정 상세 내용 |
    | **author** | String | 작성자 아이디/이메일 |
    | **createdAt** | LocalDateTime | 생성 일시 |
    | **modifiedAt** | LocalDateTime | 최종 수정 일시 |

---

##  일정 조회 (Get Schedules)

**URL:** `GET /schedules`  
**Description:** 특정 작성자가 등록한 모든 일정 목록을 조회합니다.

### 요청 (Request)
* **Query String**
    | 이름 | 데이터 타입 | 필수 여부 | 설명 |
    | :--- | :--- | :---: | :--- |
    | **author** | String | Y | 조회할 작성자 식별값 |

###  응답 (Response)
* **성공 응답 (200 OK)**
  * `List<GetScheduleResponse>` 형식으로 반환됩니다.
    | 이름 | 데이터 타입 | 설명 |
    | :--- | :--- | :--- |
    | **id** | Long | 일정 고유 ID |
    | **name** | String | 작성자 이름 |
    | **title** | String | 일정 제목 |
    | **content** | String | 일정 내용 |
    | **author** | String | 작성자 식별자 |
    | **createdAt** | LocalDateTime | 생성 일시 |
    | **modifiedAt** | LocalDateTime | 수정 일시 |

---

## 일정 수정 (Update Schedule)

**URL:** `PATCH /schedules/{id}`  
**Description:** 특정 일정의 제목이나 작성자 정보를 수정합니다. 비밀번호 검증이 필요합니다.

### 요청 (Request)
* **Path Variable**
    | 이름 | 데이터 타입 | 설명 |
    | :--- | :--- | :--- |
    | **id** | Long | 수정할 일정의 고유 번호 |

* **Body** (`UpdateScheduleRequest`)
    | 이름 | 데이터 타입 | 설명 |
    | :--- | :--- | :--- |
    | **author** | String | 변경할 작성자 식별자 |
    | **title** | String | 변경할 일정 제목 |
    | **password** | String | **(필수)** 본인 확인용 비밀번호 |

### 응답 (Response)
* **성공 응답 (200 OK)**
    | 이름 | 데이터 타입 | 설명 |
    | :--- | :--- | :--- |
    | **id** | Long | 일정 고유 ID |
    | **name** | String | 작성자 이름 |
    | **title** | String | 수정된 제목 |
    | **author** | String | 수정된 작성자 식별자 |
    | **modifiedAt** | LocalDateTime | 수정 완료 시간 |

* **실패 응답**
    * `401 Unauthorized`: 비밀번호 불일치
    * `404 Not Found`: 존재하지 않는 일정 ID

---

## 일정 삭제 (Delete Schedule)

**URL:** `DELETE /schedules/{id}`  
**Description:** 특정 일정을 시스템에서 영구 삭제합니다.

### 요청 (Request)
* **Path Variable**
    | 이름 | 데이터 타입 | 설명 |
    | :--- | :--- | :--- |
    | **id** | Long | 삭제할 일정의 고유 번호 |

* **Body** (`DeleteScheduleRequest`)
    | 이름 | 데이터 타입 | 설명 |
    | :--- | :--- | :--- |
    | **password** | String | **(필수)** 삭제 권한 확인용 비밀번호 |

### 응답 (Response)
* **성공 응답 (204 No Content)**
    * 데이터 없이 HTTP Status만 반환됩니다.
* **실패 응답**
    * `401 Unauthorized`: 비밀번호 불일치
    * `404 Not Found`: 존재하지 않는 일정 ID

<img width="444" height="245" alt="Image" src="https://github.com/user-attachments/assets/ed83fbea-d56f-45d9-8836-bef1bd4404cc" />
