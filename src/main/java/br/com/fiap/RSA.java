// java
package br.com.fiap;

import java.math.BigInteger;

public class RSA {

    public BigInteger p = new BigInteger("23"); // primeiro primo
    public BigInteger q = new BigInteger("7"); // segundo primo
    public BigInteger n = p.multiply(q); // módulo
    public BigInteger r = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
    public BigInteger e = new BigInteger("7"); // expoente público
    public BigInteger d = new BigInteger("19"); // expoente privado

    // Construtor padrão: escolhe e e calcula d
    public RSA() {
    }

    // Getters
    public BigInteger getE() { return e; }
    public BigInteger getN() { return n; }

    // Criptografar usando campos da instância
    public String criptografar(String mensagem) {
        StringBuilder sb = new StringBuilder();
        byte[] bytesMensagem = mensagem.getBytes();
        for (byte b : bytesMensagem) {
            BigInteger msgBigInt = BigInteger.valueOf(b & 0xFF);
            BigInteger msgCripto = msgBigInt.modPow(this.e, this.n);
            sb.append(msgCripto).append(" ");
        }
        return sb.toString().trim();
    }

    // Criptografar usando chave pública externa (BigInteger)
    public String criptografar(String mensagem, BigInteger ePub, BigInteger nPub) {
        StringBuilder sb = new StringBuilder();
        byte[] bytesMensagem = mensagem.getBytes();
        for (byte b : bytesMensagem) {
            BigInteger msgBigInt = BigInteger.valueOf(b & 0xFF);
            BigInteger msgCripto = msgBigInt.modPow(ePub, nPub);
            sb.append(msgCripto).append(" ");
        }
        return sb.toString().trim();
    }


    // Descriptografar usando campos da instância
    public String descriptografar(String mensagemCriptografada) {
        StringBuilder sb = new StringBuilder();
        String[] partes = mensagemCriptografada.trim().split("\\s+");
        for (String parte : partes) {
            BigInteger msgBigInt = new BigInteger(parte);
            BigInteger msgDescripto = msgBigInt.modPow(this.d, this.n);
            sb.append((char) msgDescripto.intValue());
        }
        return sb.toString();
    }

    // Exibir chaves
    public void exibirChaves() {
        System.out.println("Chave Pública: (e=" + e + ", n=" + n + ")");
        System.out.println("Chave Privada: (d=" + d + ", n=" + n + ")");
    }
}