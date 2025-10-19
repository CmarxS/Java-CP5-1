# 🔐 Projeto CP5 - Comunicação Cliente-Servidor com Criptografia RSA

## 📋 Descrição do Projeto

Este projeto implementa um sistema de comunicação segura entre Cliente e Servidor utilizando o algoritmo de criptografia **RSA (Rivest-Shamir-Adleman)**. A aplicação demonstra na prática como funciona a troca de chaves públicas e a comunicação criptografada através de sockets TCP/IP.

O projeto foi desenvolvido como parte do **Checkpoint 5** da disciplina, com foco em segurança da informação e programação de redes.

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

![Screenshot da IDE IntelliJ IDEA]
<!-- 📸 COLE AQUI: Screenshot da IDE IntelliJ IDEA mostrando a estrutura do projeto -->

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

![Estrutura do Projeto]
<!-- 📸 COLE AQUI: Screenshot da estrutura de pastas do projeto no IntelliJ -->

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

### Exemplo de Criptografia

Para a mensagem **"OLA"**:

1. **Mensagem Original**: OLA
2. **Valores ASCII**: 079 076 065
3. **Mensagem Criptografada**: 37 97 107
4. **Mensagem Descriptografada**: 79 76 65
5. **Resultado**: OLA ✅

![Teste do RSA]
<!-- 📸 COLE AQUI: Screenshot da execução do TestRSA.java mostrando os valores acima -->

### Fórmulas Matemáticas

**Criptografia:**
```
C = M^e mod N
```
Onde:
- C = mensagem criptografada
- M = mensagem original (valor ASCII)
- e = expoente público
- N = módulo

**Descriptografia:**
```
M = C^d mod N
```
Onde:
- M = mensagem descriptografada
- C = mensagem criptografada
- d = expoente privado
- N = módulo

---

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

### Execução

#### Passo 1: Iniciar o Servidor

Abra um terminal e execute:

```bash
java -cp target\classes br.com.fiap.Server
```

![Servidor Iniciado]
<!-- 📸 COLE AQUI: Screenshot do servidor iniciado, aguardando conexão -->

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

![Cliente Executando]
<!-- 📸 COLE AQUI: Screenshot do cliente solicitando a mensagem -->

---

## 📊 Resultados e Exemplos

### Exemplo 1: Enviando "OLA"

#### Saída do Cliente:

```
╔════════════════════════════════════════╗
║         CLIENTE TCP - CP5              ║
║      Conectando ao servidor...         ║
╚════════════════════════════════════════╝

[CLIENTE] ✅ Conectado ao servidor em localhost:5000

[CLIENTE] Minhas chaves:
Chave Pública: (e=7, n=161)
Chave Privada: (d=19, n=161)

╔════════════════════════════════════════╗
║       ETAPA 1: TROCA DE CHAVES         ║
╚════════════════════════════════════════╝

[CLIENTE] Recebido chave pública do servidor: 7,161
[CLIENTE] Chave do servidor: e=7, n=161
[CLIENTE] Enviando minha chave pública: (7, 161)

╔════════════════════════════════════════╗
║    ETAPA 2: COMUNICAÇÃO CRIPTOGRAFADA  ║
╚════════════════════════════════════════╝

[CLIENTE] Digite a mensagem a ser enviada: OLA
[CLIENTE] Mensagem original: OLA
[CLIENTE] Mensagem na forma numérica: 079 076 065
[CLIENTE] Mensagem criptografada: 37 97 107
[CLIENTE] ✅ Mensagem enviada

[CLIENTE] Resposta criptografada recebida: 143 109 143 32 ...
[CLIENTE] Resposta descriptografada: ACK - Recebi: OLA
```

![Saída Completa do Cliente]
<!-- 📸 COLE AQUI: Screenshot completo da saída do cliente após enviar "OLA" -->

#### Saída do Servidor:

```
╔════════════════════════════════════════╗
║         SERVIDOR TCP - CP5             ║
║      Aguardando conexão...             ║
╚════════════════════════════════════════╝

[SERVIDOR] Aguardando na porta 5000...
[SERVIDOR] ✅ Cliente conectado! IP: 127.0.0.1

[SERVIDOR] Minhas chaves:
Chave Pública: (e=7, n=161)
Chave Privada: (d=19, n=161)

╔════════════════════════════════════════╗
║       ETAPA 1: TROCA DE CHAVES         ║
╚════════════════════════════════════════╝

[SERVIDOR] Enviando chave pública: (7, 161)
[SERVIDOR] Recebido chave pública do cliente: 7,161
[SERVIDOR] Chave do cliente: e=7, n=161

╔════════════════════════════════════════╗
║    ETAPA 2: COMUNICAÇÃO CRIPTOGRAFADA  ║
╚════════════════════════════════════════╝

[SERVIDOR] 📩 Mensagem criptografada recebida:
           37 97 107

[SERVIDOR] 🔓 Mensagem descriptografada:
           OLA

[SERVIDOR] Preparando resposta: ACK - Recebi: OLA
[SERVIDOR] Resposta criptografada: 143 109 143 32 ...
[SERVIDOR] ✅ Resposta enviada
```

![Saída Completa do Servidor]
<!-- 📸 COLE AQUI: Screenshot completo da saída do servidor ao receber "OLA" -->

---

### Exemplo 2: Enviando "TESTE"

#### Saída do Cliente (Resumida):

```
[CLIENTE] Digite a mensagem a ser enviada: TESTE
[CLIENTE] Mensagem original: TESTE
[CLIENTE] Mensagem na forma numérica: 084 069 083 084 069
[CLIENTE] Mensagem criptografada: 97 33 16 97 33
[CLIENTE] ✅ Mensagem enviada
```

![Cliente enviando TESTE]
<!-- 📸 COLE AQUI: Screenshot do cliente enviando a palavra "TESTE" -->

#### Saída do Servidor (Resumida):

```
[SERVIDOR] 📩 Mensagem criptografada recebida:
           97 33 16 97 33

[SERVIDOR] 🔓 Mensagem descriptografada:
           TESTE
```

![Servidor recebendo TESTE]
<!-- 📸 COLE AQUI: Screenshot do servidor recebendo e descriptografando "TESTE" -->

---

## 🧪 Teste Unitário do RSA

O projeto inclui uma classe `TestRSA.java` para validação dos valores:

### Como Executar o Teste:

```bash
java -cp target\classes br.com.fiap.TestRSA
```

### Resultado Esperado:

```
=== TESTE RSA - Valores da Drexel University ===
p = 23
q = 7
N = p*q = 161
r = (p-1)*(q-1) = 132
e = 7
d = 19

Mensagem = OLA
Mensagem na forma numerica:
079 076 065 

Mensagem encriptada:
37 97 107

Mensagem descriptografada:
79 76 65 

Mensagem decodificada:
OLA

✓ TESTE PASSOU! Resultados correspondem exatamente aos da Drexel University.
```

![Teste RSA Executado]
<!-- 📸 COLE AQUI: Screenshot do teste unitário do RSA sendo executado com sucesso -->

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

![Código RSA.java]
<!-- 📸 COLE AQUI: Screenshot do código da classe RSA.java no IntelliJ -->

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

![Código Server.java]
<!-- 📸 COLE AQUI: Screenshot do código da classe Server.java no IntelliJ -->

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

![Código Client.java]
<!-- 📸 COLE AQUI: Screenshot do código da classe Client.java no IntelliJ -->

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
