# 🔐 Projeto CP5 - Comunicação Cliente-Servidor com Criptografia RSA

## Autores 
- **RM555997** - Caio Marques
- **RM558640** - Caio Amarante
- **RM556325** - Felipe Camargo Revolta e Souza
- **Turma** - 2TDSR

## 📋 Descrição do Projeto

Este projeto implementa um sistema de comunicação segura entre Cliente e Servidor utilizando o algoritmo de criptografia **RSA (Rivest-Shamir-Adleman)**. A aplicação demonstra na prática como funciona a troca de chaves públicas e a comunicação criptografada através de sockets TCP/IP.

O projeto foi desenvolvido como parte do **Checkpoint 5** da disciplina Java Advanced.

---

## 🎯 Objetivos

- Implementar o algoritmo RSA para criptografia e descriptografia de mensagens
- Estabelecer comunicação segura entre cliente e servidor usando sockets TCP
- Demonstrar o processo de troca de chaves públicas (handshake)
- Permitir entrada interativa de mensagens pelo usuário
- Visualizar o processo completo de criptografia e descriptografia

---

## 🛠️ IDE Utilizada

**IntelliJ IDEA** - IDE utilizada para o desenvolvimento completo do projeto.

<img width="77" height="88" alt="image" src="https://github.com/user-attachments/assets/88edf077-cfc7-430e-ae87-610d4fc484bc" />

---

## 🏗️ Arquitetura do Sistema

### Estrutura de Arquivos

```
CP5-Parte-1/
│
├── src/
│   └── main/
│       └── java/
│           └── br/
│               └── com/
│                   └── fiap/
│                       ├── RSA.java          # Classe com algoritmo RSA
│                       ├── Server.java       # Servidor TCP
│                       ├── Client.java       # Cliente TCP
│                       └── TestRSA.java      # Teste unitário do RSA
│
├── target/
│   └── classes/                              # Arquivos compilados
│
├── pom.xml                                   # Configuração Maven
└── README.md                                 # Este arquivo
```

---

## 🔢 Algoritmo RSA Implementado

### Parâmetros Utilizados

O projeto utiliza valores específicos validados pela calculadora RSA da Drexel University:

| Parâmetro | Valor | Descrição |
|-----------|-------|-----------|
| **p** | 23 | Primeiro número primo |
| **q** | 7 | Segundo número primo |
| **N** | 161 | Módulo (p × q) |
| **r** | 132 | φ(N) = (p-1) × (q-1) |
| **e** | 7 | Expoente público |
| **d** | 19 | Expoente privado |

### Execução

#### Passo 1: Iniciar o Servidor

Abra um terminal e execute:

```bash
java -cp target\classes br.com.fiap.Server
```

#### Passo 2: Iniciar o Cliente

Abra **outro terminal** e execute:

```bash
java -cp target\classes br.com.fiap.Client
```

#### Passo 3: Digitar a Mensagem

Quando solicitado, digite a mensagem que deseja enviar (exemplo: "OLA"):

```
[CLIENTE] Digite a mensagem a ser enviada: OLA
```

---

### Exemplo de Criptografia

Para a mensagem **"OLA"**:

1. **Mensagem Original**: OLA
2. **Valores ASCII**: 079 076 065
3. **Mensagem Criptografada**: 37 97 107
4. **Mensagem Descriptografada**: 79 76 65
5. **Resultado**: OLA ✅


### Exemplo:
Inicializando o Server:
<img width="1622" height="306" alt="image" src="https://github.com/user-attachments/assets/8eb6649e-e712-48b8-89b3-10a55e4b067b" />

Enviando a mensagem OLA pelo Client:
<img width="1353" height="780" alt="image" src="https://github.com/user-attachments/assets/4830fb31-cf01-4d01-8196-0467f8714bfb" />

Resultado:
- Client:
<img width="715" height="397" alt="image" src="https://github.com/user-attachments/assets/60721f1e-939e-40cd-84ff-05ae26ae5e8f" />

- Server:
<img width="781" height="680" alt="image" src="https://github.com/user-attachments/assets/8f025905-c8a3-416a-8083-51a28394854b" />

---

### Print calculadora RSA - Drexel University:
<img width="1920" height="884" alt="image" src="https://github.com/user-attachments/assets/c97a36b1-e2fb-4c96-a421-7d825bafd855" />
<img width="1898" height="277" alt="image" src="https://github.com/user-attachments/assets/8757e680-0c1a-495c-becb-27ee40250e37" />
<img width="1893" height="625" alt="image" src="https://github.com/user-attachments/assets/995a0212-a6d7-4f6e-8942-5075acd1f20f" />


## 🔄 Fluxo de Comunicação

### Diagrama de Sequência

```
┌─────────┐                                    ┌─────────┐
│ Cliente │                                    │ Servidor│
└────┬────┘                                    └────┬────┘
     │                                              │
     │  1. Conecta ao Servidor (porta 5000)        │
     │─────────────────────────────────────────────>│
     │                                              │
     │  2. Servidor envia Chave Pública (e,n)      │
     │<─────────────────────────────────────────────│
     │                                              │
     │  3. Cliente envia Chave Pública (e,n)       │
     │─────────────────────────────────────────────>│
     │                                              │
     │  4. Cliente criptografa e envia mensagem    │
     │─────────────────────────────────────────────>│
     │                                              │
     │                    5. Servidor descriptografa│
     │                       processa e criptografa │
     │                       resposta               │
     │                                              │
     │  6. Servidor envia resposta criptografada   │
     │<─────────────────────────────────────────────│
     │                                              │
     │  7. Cliente descriptografa resposta         │
     │                                              │
     │  8. Encerra conexão                         │
     │<────────────────────────────────────────────>│
     │                                              │
```

---

## 💻 Como Executar o Projeto

### Pré-requisitos

- **Java JDK** 8 ou superior
- **Maven** (opcional, para gerenciamento de dependências)
- **IntelliJ IDEA** (ou outra IDE Java)

### Compilação

#### Opção 1: Usando Maven
```bash
mvn clean compile
```

#### Opção 2: Usando javac
```bash
javac -d target\classes src\main\java\br\com\fiap\*.java
```
---

## 📝 Descrição das Classes

### 1. RSA.java

Implementa o algoritmo de criptografia RSA.

**Atributos principais:**
- `p`, `q`: Números primos
- `n`: Módulo (p × q)
- `r`: φ(N) = (p-1) × (q-1)
- `e`: Expoente público
- `d`: Expoente privado

**Métodos principais:**
- `criptografar(String mensagem)`: Criptografa usando chave própria
- `criptografar(String mensagem, BigInteger e, BigInteger n)`: Criptografa usando chave externa
- `descriptografar(String mensagemCriptografada)`: Descriptografa usando chave privada
- `exibirChaves()`: Exibe as chaves pública e privada

---

### 2. Server.java

Implementa o servidor TCP que aguarda conexões.

**Funcionalidades:**
- Aguarda conexão na porta 5000
- Realiza troca de chaves públicas com o cliente
- Recebe mensagem criptografada
- Descriptografa e exibe a mensagem original
- Envia resposta criptografada de volta ao cliente

**Métodos principais:**
- `iniciar()`: Inicia o servidor e aguarda conexões
- `trocarChaves()`: Realiza troca de chaves públicas
- `comunicar()`: Gerencia a comunicação criptografada
- `fecharConexao()`: Encerra a conexão

---

### 3. Client.java

Implementa o cliente TCP que se conecta ao servidor.

**Funcionalidades:**
- Conecta ao servidor na porta 5000
- Realiza troca de chaves públicas com o servidor
- Permite ao usuário digitar mensagem via Scanner
- Exibe mensagem original e valores ASCII
- Criptografa e envia mensagem
- Recebe e descriptografa resposta do servidor

**Métodos principais:**
- `conectar()`: Estabelece conexão com o servidor
- `trocarChaves()`: Realiza troca de chaves públicas
- `comunicar()`: Gerencia entrada do usuário e comunicação criptografada
- `fecharConexao()`: Encerra a conexão
---

## 🔒 Conceitos de Segurança Aplicados

### 1. Criptografia Assimétrica

O RSA é um algoritmo de **criptografia assimétrica**, onde:
- **Chave Pública** (e, n): Usada para criptografar mensagens
- **Chave Privada** (d, n): Usada para descriptografar mensagens

### 2. Troca de Chaves (Key Exchange)

Antes da comunicação criptografada, cliente e servidor trocam suas chaves públicas. Isso permite que:
- O cliente criptografe mensagens usando a chave pública do servidor
- O servidor criptografe respostas usando a chave pública do cliente

### 3. Confidencialidade

Mesmo que um interceptador capture a mensagem criptografada, não conseguirá descriptografá-la sem a chave privada correspondente.

### Diagrama de Troca de Chaves:

```
┌─────────┐                           ┌─────────┐
│ Cliente │                           │ Servidor│
│         │                           │         │
│ Chave   │  ──────────────────────>  │         │
│ Pública │  Envia (e_cliente, n)     │         │
│ (e, n)  │                           │         │
│         │  <──────────────────────  │ Chave   │
│         │  Recebe (e_servidor, n)   │ Pública │
│         │                           │ (e, n)  │
│         │                           │         │
│ Chave   │                           │ Chave   │
│ Privada │                           │ Privada │
│ (d, n)  │                           │ (d, n)  │
│ SECRETA │                           │ SECRETA │
└─────────┘                           └─────────┘
```

---

## 📚 Tecnologias Utilizadas

| Tecnologia | Versão | Uso |
|------------|--------|-----|
| Java | JDK 8+ | Linguagem de programação |
| Maven | 3.x | Gerenciamento de dependências |
| Java Sockets | Built-in | Comunicação TCP/IP |
| BigInteger | java.math | Operações com números grandes |
| Scanner | java.util | Entrada de dados do usuário |

---
