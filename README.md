# FullstackMonitoring

## Descrição do Projeto

FullstackMonitoring é um sistema de monitoramento de dispositivos que permite rastrear e gerenciar diversos dispositivos em tempo real. O projeto foi desenvolvido usando Spring Boot e oferece uma API RESTful para interação com o sistema.
Criado por Matheus José de Lima Costa.

[https://github.com/MatheusCosta616]

## Funcionalidades

- Gerenciamento de dispositivos (criar, ler, atualizar, deletar)
- Configuração de alertas para dispositivos
- Recuperação de alertas por dispositivo

## Tecnologias Utilizadas

- Java 17
- Spring Boot 3.3.3
- Spring Data JPA
- PostgreSQL
- Lombok
- JUnit 5 e Mockito para testes

## Configuração do Projeto

### Pré-requisitos

- JDK 17
- Maven
- PostgreSQL

### Configuração do Banco de Dados

1. Crie um banco de dados PostgreSQL chamado `fullstackDB`.
2. Atualize o arquivo `src/main/resources/application.properties` com suas credenciais de banco de dados:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/fullstackDB
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

### Executando o Projeto

1. Clone o repositório:
   ```
   git clone https://github.com/seu-usuario/FullstackMonitoring.git
   ```

2. Navegue até a pasta do projeto:
   ```
   cd FullstackMonitoring
   ```

3. Compile o projeto:
   ```
   mvn clean install
   ```

4. Execute a aplicação:
   ```
   mvn spring-boot:run
   ```

A aplicação estará disponível em `http://localhost:8080`.

## Uso da API

### Dispositivos

- Criar um dispositivo:
  ```
  POST /devices
  Body: {
    "name": "Dispositivo 1",
    "status": "Ativo",
    "lastPing": "2023-04-15T10:30:00Z",
    "location": "Sala 1",
    "logs": "Inicialização concluída"
  }
  ```

- Listar todos os dispositivos:
  ```
  GET /devices
  ```

- Obter um dispositivo específico:
  ```
  GET /devices/{deviceId}
  ```

- Atualizar um dispositivo:
  ```
  PUT /devices/{deviceId}
  Body: {
    "name": "Dispositivo 1 Atualizado",
    "status": "Inativo",
    "lastPing": "2023-04-15T11:30:00Z",
    "location": "Sala 2",
    "logs": "Manutenção realizada"
  }
  ```

- Deletar um dispositivo:
  ```
  DELETE /devices/{deviceId}
  ```

### Alertas

- Configurar um alerta:
  ```
  POST /alerts
  Body: {
    "deviceId": "uuid-do-dispositivo",
    "condition": "Temperatura > 30°C",
    "message": "Temperatura alta detectada"
  }
  ```

- Obter alertas de um dispositivo:
  ```
  GET /alerts/device/{deviceId}
  ```

## Testes

O projeto inclui testes unitários para os serviços. Para executar os testes:

```
mvn test
```

## Estrutura do Projeto

- `src/main/java/com/fullstackmonitoring/`
  - `controller/`: Controladores REST
  - `dto/`: Objetos de Transferência de Dados
  - `model/`: Entidades JPA
  - `repository/`: Interfaces de Repositório Spring Data
  - `service/`: Serviços de negócios

- `src/test/java/com/fullstackmonitoring/`
  - `service/`: Testes unitários para serviços

## Contribuindo

Contribuições são bem-vindas! Por favor, sinta-se à vontade para submeter pull requests.

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Faça commit das suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request


## Contato

Seu Nome - matheus.costa2616@gmail.com

Link do Projeto: [https://https://github.com/MatheusCosta616/FullstackMonitoringAPI](https://https://github.com/MatheusCosta616/FullstackMonitoringAPI)