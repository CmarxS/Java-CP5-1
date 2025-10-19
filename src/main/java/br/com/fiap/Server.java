package br.com.fiap;
import java.io.*;
import java.net.*;
import java.math.BigInteger;

/**
 * Servidor TCP com Criptografia RSA
 * Aguarda conexão do cliente, troca chaves e comunica com mensagens criptografadas
 */
public class Server {

    private static final int PORTA = 5000;
    private RSA rsa;
    private Socket socket;
    private PrintWriter saida;
    private BufferedReader entrada;
    private BigInteger eCliente;
    private BigInteger nCliente;

    public Server() {
        this.rsa = new RSA();
    }

    public void iniciar() {
        try {
            System.out.println("╔════════════════════════════════════════╗");
            System.out.println("║         SERVIDOR TCP - CP5             ║");
            System.out.println("║      Aguardando conexão...             ║");
            System.out.println("╚════════════════════════════════════════╝\n");

            // Cria ServerSocket
            ServerSocket serverSocket = new ServerSocket(PORTA);
            System.out.println("[SERVIDOR] Aguardando na porta " + PORTA + "...");

            // Aceita conexão do cliente
            socket = serverSocket.accept();
            System.out.println("[SERVIDOR] ✅ Cliente conectado! IP: " + socket.getInetAddress().getHostAddress());

            // Inicializa streams
            saida = new PrintWriter(socket.getOutputStream(), true);
            entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Exibe as chaves do servidor
            System.out.println("\n[SERVIDOR] Minhas chaves:");
            rsa.exibirChaves();

            // ETAPA 1: Troca de chaves públicas
            trocarChaves();

            // ETAPA 2: Comunicação com criptografia
            comunicar();

            // Fecha conexão
            fecharConexao();
            serverSocket.close();

        } catch (IOException e) {
            System.out.println("[ERRO] " + e.getMessage());
        }
    }

    private void trocarChaves() throws IOException {
        System.out.println("\n╔═══════════════════════════════════════���╗");
        System.out.println("║       ETAPA 1: TROCA DE CHAVES         ║");
        System.out.println("╚════════════════════════════════════════╝\n");

        String minhaChavePublica = rsa.getE() + "," + rsa.getN();
        saida.println(minhaChavePublica);
        System.out.println("[SERVIDOR] Enviando chave pública: (" + rsa.getE() + ", " + rsa.getN() + ")");

        String chavePublicaCliente = entrada.readLine();
        System.out.println("[SERVIDOR] Recebido chave pública do cliente: " + chavePublicaCliente);

        if (chavePublicaCliente != null && !chavePublicaCliente.trim().isEmpty()) {
            String[] partes = chavePublicaCliente.split(",");
            eCliente = new BigInteger(partes[0]);
            nCliente = new BigInteger(partes[1]);
            System.out.println("[SERVIDOR] Chave do cliente: e=" + eCliente + ", n=" + nCliente);
        } else {
            throw new IOException("Chave pública do cliente inválida");
        }
    }

    private void comunicar() throws IOException {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║    ETAPA 2: COMUNICAÇÃO CRIPTOGRAFADA  ║");
        System.out.println("╚════════════════════════════════════════╝\n");

        // Recebe mensagem criptografada do cliente
        String mensagemCripto = entrada.readLine();
        System.out.println("[SERVIDOR] 📩 Mensagem criptografada recebida:");
        System.out.println("           " + mensagemCripto);

        // Descriptografa usando sua chave privada
        String mensagemOriginal = rsa.descriptografar(mensagemCripto);
        System.out.println("\n[SERVIDOR] 🔓 Mensagem descriptografada:");
        System.out.println("           " + mensagemOriginal);

        // Prepara resposta
        System.out.println("\n[SERVIDOR] Preparando resposta: " + mensagemOriginal);

        // Criptografa resposta usando chave pública do cliente
        String respostaCripto = rsa.criptografar(mensagemOriginal, eCliente, nCliente);
        System.out.println("[SERVIDOR] Resposta criptografada: " + respostaCripto);

        // Envia resposta criptografada
        saida.println(respostaCripto);
        System.out.println("[SERVIDOR] ✅ Resposta enviada");
    }

    private void fecharConexao() throws IOException {
        System.out.println("\n╔═══════════════════════════════���═══════���╗");
        System.out.println("║         ETAPA 3: DESCONEXÃO           ║");
        System.out.println("╚═══════════════════════════════���═══════���╝\n");

        entrada.close();
        saida.close();
        socket.close();
        System.out.println("[SERVIDOR] ✅ Conexão encerrada\n");
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.iniciar();
    }
}