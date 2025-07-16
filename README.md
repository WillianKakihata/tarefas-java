# Tarefas Projeto (backend)

## Como rodar a aplicação

1. Certifique-se de ter o Docker e o Docker Compose instalados.

2. No terminal, na raiz do projeto (onde está o `compose.yaml`), rode:

```bash
docker compose up --build
```

3. Aguarde o container buildar e iniciar.

## Acessando a aplicação
### Jsons da aplicação:  
1. Acessar o link, onde está localizado no OneDrive, tasks.json   
https://1drv.ms/f/c/7e3395b51ee92213/En3bqeExiqdPjCjugH9Z6t4B_EXfrtTf8jdCBhn6i5oJPw?e=kOoUxF (Qualquer e-mail, da microsoft)  
3. Baixar o tasks.json  
4. Insira o tasks.json no Postman ou insomnia.  


ou criar as rotas:  
- **URL (PORTA)**: `http://localhost:8081`

--- 

### ➕ Registrar Usuario
- **URL**: `/auth/register`
- **Método**: `POST`
- **Request Body**:
```json

{
	"login": "ADMIN1",
	"password": "senha123",
	"role": "ADMIN" // <- ADMIN OU USER
}

```
- **Resposta**: `200 Okay` (sem corpo)

---

### ➕ Logar Usuario
- **URL**: `/auth/login`
- **Método**: `POST`
- **Request Body**:
```json

{
	"login": "ADMIN1",
	"password": "senha123"
}

```
- **Resposta**: `200 Okay`
```json
{
	"token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhdXRoLWFwaSIsInN1YiI6IkFETUlOMSIsInJvbGUiOiJhZG1pbiIsImV4cCI6MTc0ODQyNTgxN30.pFzpvBViw4baPeiIPDentk8s2O-qgx1JX2u0to125Jk"
}
```
---
## Para que as rotas seguintes funcionem é necessário utilizar o Bearer Token, e inserir o Token JWT:  
![image](https://github.com/user-attachments/assets/8588bd0f-64ba-4703-ab95-0f4631e57606)
  
---


### ➕ Criar Tarefas (APENAS ADMINS)
- **URL**: `/tarefas`
- **Método**: `POST`
- **Request Body**:
```json

	{
		"titulo": "titulo",
		"descricao": "ver se da bom",
		"status": "INICIO"
	}

```


---
### 📄 Listar Todos as Tarefas (APENAS USUARIOS E ADMINS)
- **URL**: `/tarefas`
- **Método**: `GET`
- **Resposta**:
```json
[
	{
		"titulo": "titulo",
		"descricao": "ver se da bom",
		"status": "INICIO"
	}
]
```

---

### 📄 Listar uma das Tarefas (APENAS USUARIOS E ADMINS)
- **URL**: `/tarefas/{id}`
- **Método**: `GET`
- **Resposta**:
```json
	{
		"titulo": "titulo",
		"descricao": "ver se da bom",
		"status": "INICIO"
	}
```

---

### 🔄 Atualizar Tarefas (APENAS ADMINS)
- **URL**: `/tarefas/{id}`
- **Método**: `PUT`
- **Parâmetro**: `id` (long) — ID da tarefa a ser atualizado
- **Request Body**:

- **Resposta**: Objeto atualizado `204 No Content`

---

### ❌ Deletar Tarefas (APENAS ADMINS)
- **URL**: `/tarefas/{id}`
- **Método**: `DELETE`
- **Parâmetro**: `id` (long) — ID da tarefa a ser atualizado
- **Resposta**: `204 No Content`

---

## Comandos úteis

- Parar a aplicação:  
  ```bash
  docker compose down
  ```

- Rodar em modo detach (em background):  
  ```bash
  docker compose up --build -d
  ```

---
