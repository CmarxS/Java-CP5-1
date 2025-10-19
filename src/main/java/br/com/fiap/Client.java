package br.com.fiap;
import java.io.*;
import java.net.*;
import java.math.BigInteger;
import java.util.Scanner;

/**
 * Cliente TCP com Criptografia RSA
 * Conecta ao servidor, troca chaves e comunica com mensagens criptografadas
 */
public class Client {

    private static final String HOST = "localhost";
    private static final int PORTA = 5000;
    private RSA rsa;
    private Socket socket;
    private PrintWriter saida;
    private BufferedReader entrada;
    private BigInteger eServidor;
    private BigInteger nServidor;
    private Scanner scanner;

    public Client() {
        // Valores da planilha: n=161, e=3, d=235
        this.rsa = new RSA();
        this.scanner = new Scanner(System.in);
    }

    public void conectar() {
        try {
            System.out.println("╔═══════════════════════���════════════════╗");
            System.out.println("║         CLIENTE TCP - CP5              ║");
            System.out.println("║      Conectando ao servidor...         ║");
            System.out.println("╚═══════════════════════════════════════���╝\n");

            // Conecta ao servidor
            socket = new Socket(HOST, PORTA);
            System.out.println("[CLIENTE] ✅ Conectado ao servidor em " + HOST + ":" + PORTA);

            // Inicializa streams
            saida = new PrintWriter(socket.getOutputStream(), true);
            entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Exibe as chaves do cliente
            System.out.println("\n[CLIENTE] Minhas chaves:");
            rsa.exibirChaves();

            // ETAPA 1: Troca de chaves públicas
            trocarChaves();

            // ETAPA 2: Comunicação com criptografia
            comunicar();

            // Fecha conexão
            fecharConexao();

        } catch (ConnectException e) {
            System.out.println("[ERRO] Não foi possível conectar ao servidor!");
            System.out.println("[ERRO] Certifique-se de que o servidor está rodando na porta " + PORTA);
        } catch (IOException e) {
            System.out.println("[ERRO] " + e.getMessage());
        }
    }

    /**
     * Troca as chaves públicas com o servidor
     */
    private void trocarChaves() throws IOException {
        System.out.println("\n╔═══════════════════════════════════════���╗");
        System.out.println("║       ETAPA 1: TROCA DE CHAVES         ║");
        System.out.println("╚═══════════════════════���════════════════╝\n");

        // Recebe chave pública do servidor
        String chavePublicaServidor = entrada.readLine();
        System.out.println("[CLIENTE] Recebido chave pública do servidor: " + chavePublicaServidor);

        // Parseia chave do servidor (formato: e,n)
        String[] partes = chavePublicaServidor.split(",");
        eServidor = new BigInteger(partes[0]);
        nServidor = new BigInteger(partes[1]);
        System.out.println("[CLIENTE] Chave do servidor: e=" + eServidor + ", n=" + nServidor);

        // Envia sua chave pública (e,n) para o servidor
        String minhaChavePublica = rsa.getE() + "," + rsa.getN();
        saida.println(minhaChavePublica);
        System.out.println("[CLIENTE] Enviando minha chave pública: (" + rsa.getE() + ", " + rsa.getN() + ")");
    }

    /**
     * Comunica com o servidor usando mensagens criptografadas
     */
    private void comunicar() throws IOException {
        System.out.println("\n╔═══════════════════════���═══════════════���╗");
        System.out.println("║    ETAPA 2: COMUNICAÇÃO CRIPTOGRAFADA  ║");
        System.out.println("╚════════════════════════════════════════╝\n");

        // Prepara mensagem (usar caracteres com ASCII baixo)
        System.out.print("[CLIENTE] Digite a mensagem a ser enviada: ");
        String mensagem = scanner.nextLine();
        System.out.println("[CLIENTE] Mensagem original: " + mensagem);

        // Exibe a mensagem na forma numérica (valores ASCII)
        System.out.print("[CLIENTE] Mensagem na forma numérica: ");
        for (char c : mensagem.toCharArray()) {
            System.out.printf("%03d ", (int)c);
        }
        System.out.println();

        // Criptografa usando chave pública do servidor
        String mensagemCripto = rsa.criptografar(mensagem, eServidor, nServidor);
        System.out.println("[CLIENTE] Mensagem criptografada: " + mensagemCripto);

        // Envia mensagem criptografada
        saida.println(mensagemCripto);
        System.out.println("[CLIENTE] ✅ Mensagem enviada\n");

        // Recebe resposta criptografada do servidor
        String respostaCripto = entrada.readLine();
        System.out.println("[CLIENTE] Resposta criptografada recebida: " + respostaCripto);

        // Descriptografa usando sua chave privada
        String resposta = rsa.descriptografar(respostaCripto);
        System.out.println("[CLIENTE] Resposta descriptografada: " + resposta);
    }

    private void fecharConexao() throws IOException {
        System.out.println("\n╔═══════════════════════���════════════════╗");
        System.out.println("║         ETAPA 3: DESCONEXÃO           ║");
        System.out.println("╚═══════════════════════���═══════════════���╝\n");

        entrada.close();
        saida.close();
        socket.close();
        System.out.println("[CLIENTE] ✅ Conexão encerrada\n");
    }

    public static void main(String[] args) {
        // Aguarda um pouco para o servidor iniciar
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Client client = new Client();
        client.conectar();
    }
}