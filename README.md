<div align="center">

<img src="https://img.shields.io/badge/Talent-Bridge-0A0A0A?style=for-the-badge&logoColor=white" alt="Talent Bridge" height="60"/>

### AI-powered platform that matches candidates to jobs and generates tailored resumes using GPT-4o

<br/>

[![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://openjdk.org/projects/jdk/21/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.1.0-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Spring AI](https://img.shields.io/badge/Spring_AI-2.0.0-6DB33F?style=for-the-badge&logo=spring&logoColor=white)](https://spring.io/projects/spring-ai)
[![MongoDB](https://img.shields.io/badge/MongoDB-7.0-47A248?style=for-the-badge&logo=mongodb&logoColor=white)](https://www.mongodb.com/)
[![OpenAI](https://img.shields.io/badge/GPT--4o-412991?style=for-the-badge&logo=openai&logoColor=white)](https://platform.openai.com/)
[![Docker](https://img.shields.io/badge/Docker-Compose-2496ED?style=for-the-badge&logo=docker&logoColor=white)](https://docs.docker.com/compose/)

</div>

---

## About

**Talent Bridge** is a backend REST API that uses AI to bridge the gap between candidates and job opportunities. It exposes two AI-powered features:

- **Resume Generation** — given a candidate and a job, GPT-4o rewrites the candidate's resume to be tailored and optimized for that specific role.
- **Candidate Fitting** — evaluates how well a candidate matches a job, returning a numeric score (0–100) and a qualitative status (`EXCELLENT`, `GOOD`, `AVERAGE`, `NOT_FITTING`).

---

## Features

- 📄 **Tailored Resume Generation** — AI rewrites the resume to highlight what matters most for each job
- 🎯 **Candidate-Job Fitting Score** — objective AI evaluation with score and status
- 🏗️ **Strategy + Facade Pattern** — clean extensible LLM layer; adding new AI features is a one-file change
- 🔒 **Candidate & Job Validation** — all AI requests validate entity existence before hitting the LLM
- 🐳 **Docker-ready** — MongoDB spins up with a single command

---

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 21 |
| Framework | Spring Boot 4.1.0 |
| AI Integration | Spring AI 2.0.0 + OpenAI GPT-4o |
| Database | MongoDB 7 |
| Infrastructure | Docker Compose |
| Boilerplate reduction | Lombok |

---

## Architecture

```
HTTP Request
     │
     ▼
┌─────────────────────┐
│     Controllers      │  CandidateController / JobController
│                      │  JobCandidateController / HumanResourcesController
└──────────┬──────────┘
           │
           ▼
┌─────────────────────┐
│      Services        │  CandidateService / JobService / JobCandidateService
│  (ICandidateService) │  Validates entities, throws on not-found
│  (IJobService)       │
└──────────┬──────────┘
           │
           ▼
┌─────────────────────┐
│     LLM Facade       │  LLMFacade — single generic entry point
│   execute<T>(...)    │  dispatches by LLM_EVENT enum
└──────────┬──────────┘
           │
           ▼
┌─────────────────────┐
│    LLM Utils         │  Maps LLM_EVENT → TalentStrategy<T>
│  getStrategyByEvent  │  Spring-managed — supports ChatClient injection
└──────────┬──────────┘
           │
     ┌─────┴──────┐
     ▼            ▼
┌─────────┐  ┌──────────────┐
│ Resume  │  │   Fitting    │  TalentStrategy<T> implementations
│Strategy │  │  Strategy    │
└────┬────┘  └──────┬───────┘
     └──────┬───────┘
            ▼
┌─────────────────────┐
│   LLMOpenAIClient   │  Holds ChatClient + prompt templates
│  generateResume()   │  Loaded from classpath at startup
│  evaluateFitting()  │
└──────────┬──────────┘
           │
           ▼
     OpenAI GPT-4o
```

---

## Project Structure

```
src/
├── main/
│   ├── java/talentbridge/ai/
│   │   ├── config/
│   │   │   └── ApplicationConfiguration.java   # Loads prompts + wires LLMOpenAIClient bean
│   │   ├── controller/
│   │   │   ├── CandidateController.java
│   │   │   ├── JobController.java
│   │   │   ├── JobCandidateController.java
│   │   │   └── HumanResourcesController.java   # AI endpoints
│   │   ├── dto/
│   │   │   ├── JobCandidateRequest.java
│   │   │   ├── JobDetails.java                 # AI fitting response
│   │   │   └── enumerations/FittingStatus.java
│   │   ├── facade/
│   │   │   └── LLMFacade.java
│   │   ├── llm/
│   │   │   ├── LLM_EVENT.java
│   │   │   ├── LLMOpenAIClient.java
│   │   │   ├── PromptConfig.java
│   │   │   └── JobAndCandidateLLMInput.java
│   │   ├── model/
│   │   │   ├── Candidate.java
│   │   │   ├── Job.java
│   │   │   ├── JobCandidate.java
│   │   │   └── vo/Address.java
│   │   ├── repository/
│   │   │   ├── CandidateRepository.java
│   │   │   ├── JobRepository.java
│   │   │   └── JobCandidateRepository.java
│   │   ├── service/
│   │   │   ├── ICandidateService.java
│   │   │   ├── CandidateService.java
│   │   │   ├── IJobService.java
│   │   │   ├── JobService.java
│   │   │   └── JobCandidateService.java
│   │   ├── strategies/
│   │   │   ├── TalentStrategy.java
│   │   │   ├── ResumeStrategy.java
│   │   │   └── FittingStrategy.java
│   │   └── utils/
│   │       └── LLMUtils.java
│   └── resources/
│       ├── application.yml
│       └── prompts/
│           ├── resume/
│           │   ├── system.txt
│           │   └── user.txt
│           └── fitting/
│               ├── system.txt
│               └── user.txt
```

---

## Getting Started

### Prerequisites

- Java 21+
- Maven 3.9+
- Docker & Docker Compose
- OpenAI API Key

### 1. Clone the repository

```bash
git clone https://github.com/felipematheus1337/talent-bridge.git
cd talent-bridge
```

### 2. Configure environment variables

Copy the `.env` file and fill in your values:

```bash
cp .env .env.local
```

| Variable | Description | Default |
|---|---|---|
| `MONGO_HOST` | MongoDB host | `localhost` |
| `MONGO_PORT` | MongoDB port | `27017` |
| `MONGO_DATABASE` | Database name | `talentbridge` |
| `MONGO_USERNAME` | MongoDB username | `admin` |
| `MONGO_PASSWORD` | MongoDB password | `admin` |
| `OPENAI_API_KEY` | Your OpenAI API key | — |

### 3. Start MongoDB

```bash
docker-compose up -d
```

### 4. Run the application

```bash
./mvnw spring-boot:run
```

The API will be available at `http://localhost:8080`.

---

## API Reference

### Candidates

| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/candidates` | Create a candidate |
| `GET` | `/candidates` | List all candidates |
| `GET` | `/candidates/{id}` | Get candidate by ID |

<details>
<summary>POST /candidates — Request body</summary>

```json
{
  "name": "John Doe",
  "age": 28,
  "address": {
    "street": "Rua das Flores",
    "number": 123,
    "country": "Brazil",
    "zipCode": "01310-100"
  },
  "skills": ["Java", "Spring Boot", "MongoDB"],
  "resume": "Experienced backend developer with 5 years in Java..."
}
```
</details>

---

### Jobs

| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/jobs` | Create a job |
| `GET` | `/jobs` | List all jobs |
| `GET` | `/jobs/{id}` | Get job by ID |

<details>
<summary>POST /jobs — Request body</summary>

```json
{
  "companyName": "Tech Corp",
  "description": "We are looking for a senior backend engineer with strong Java skills...",
  "baseSalary": 12000.00
}
```
</details>

---

### Job Candidates

| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/job-candidates` | Associate a candidate to a job |
| `GET` | `/job-candidates` | List all associations |

<details>
<summary>POST /job-candidates — Request body</summary>

```json
{
  "idCandidate": "665f1a2b3c4d5e6f7a8b9c0d",
  "idJob": "665f1a2b3c4d5e6f7a8b9c0e"
}
```
</details>

---

### AI — Human Resources

| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/hr/generate-resume` | Generate a tailored resume for a job |
| `POST` | `/hr/fitting` | Evaluate candidate-job fit |

<details>
<summary>POST /hr/generate-resume — Request & Response</summary>

**Request:**
```json
{
  "idCandidate": "665f1a2b3c4d5e6f7a8b9c0d",
  "idJob": "665f1a2b3c4d5e6f7a8b9c0e"
}
```

**Response:** `200 OK`
```
John Doe
john.doe@email.com | LinkedIn

PROFESSIONAL SUMMARY
Experienced backend developer with 5 years delivering high-performance...
...
```
</details>

<details>
<summary>POST /hr/fitting — Request & Response</summary>

**Request:**
```json
{
  "idCandidate": "665f1a2b3c4d5e6f7a8b9c0d",
  "idJob": "665f1a2b3c4d5e6f7a8b9c0e"
}
```

**Response:** `200 OK`
```json
{
  "score": 87,
  "status": "EXCELLENT"
}
```
</details>

---

## How the AI Layer Works

Both AI features share the same pipeline:

```
HumanResourcesController
  → LLMFacade.execute<T>(candidateId, jobId, LLM_EVENT)
    → LLMUtils.getStrategyByEvent(event)   // returns the right Spring bean
      → Strategy.execute(candidateId, jobId)
        → fetch Candidate + Job from MongoDB
          → LLMOpenAIClient.generateResume() or .evaluateFitting()
            → ChatClient with system + user prompts loaded from classpath
              → OpenAI GPT-4o
```

Prompt templates live in `src/main/resources/prompts/` and are loaded once at startup by `ApplicationConfiguration`. The user template uses `{userResume}` and `{jobDescription}` as placeholders filled at runtime via Spring AI's `ChatClient`.

For **fitting**, Spring AI's structured output (`.entity(JobDetails.class)`) automatically appends JSON schema instructions to the prompt and deserializes the response directly into the `JobDetails` record.

---

## License

This project is licensed under the MIT License.

---

<div align="center">
  Made with ☕ and AI
</div>
