package com.senac.FixIt.config;

import com.senac.FixIt.models.Chamado;
import com.senac.FixIt.models.Prioridade;
import com.senac.FixIt.models.StatusChamado;
import com.senac.FixIt.models.Usuario;
import com.senac.FixIt.repository.ChamadoRepository;
import com.senac.FixIt.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner loadInitialData(UsuarioRepository usuarioRepository,
                                             ChamadoRepository chamadoRepository,
                                             BCryptPasswordEncoder encoder) {
        return args -> {
            if (usuarioRepository.count() == 0) {

                // Usuários
                Usuario admin = new Usuario();
                admin.setName("Administrador");
                admin.setEmail("admin@fixit.com");
                admin.setPassword(encoder.encode("admin"));
                admin.setDepartment("TI");
                admin.setRole("admin");

                Usuario tecnico1 = new Usuario();
                tecnico1.setName("Guilherme");
                tecnico1.setEmail("guilherme@fixit.com");
                tecnico1.setPassword(encoder.encode("guilherme"));
                tecnico1.setDepartment("Suporte");
                tecnico1.setRole("technician");

                Usuario tecnico2 = new Usuario();
                tecnico2.setName("Caio");
                tecnico2.setEmail("caio@fixit.com");
                tecnico2.setPassword(encoder.encode("caio"));
                tecnico2.setDepartment("Suporte");
                tecnico2.setRole("technician");
                
                Usuario tecnico3 = new Usuario();
                tecnico1.setName("Gustavo");
                tecnico1.setEmail("gustavo@fixit.com");
                tecnico1.setPassword(encoder.encode("gustavo"));
                tecnico1.setDepartment("Suporte");
                tecnico1.setRole("technician");
                
                Usuario tecnico4 = new Usuario();
                tecnico1.setName("Mariana");
                tecnico1.setEmail("mariana@fixit.com");
                tecnico1.setPassword(encoder.encode("mariana"));
                tecnico1.setDepartment("Suporte");
                tecnico1.setRole("technician");

                Usuario usuario1 = new Usuario();
                usuario1.setName("Ana Silva");
                usuario1.setEmail("silvaana@fixit.com");
                usuario1.setPassword(encoder.encode("ana123"));
                usuario1.setDepartment("Comercial");
                usuario1.setRole("user");
                
                Usuario usuario2 = new Usuario();
                usuario2.setName("João Almeida");
                usuario2.setEmail("almeidajoao@fixit.com");
                usuario2.setPassword(encoder.encode("joao123"));
                usuario2.setDepartment("Financeiro");
                usuario2.setRole("user");
                
                Usuario usuario3 = new Usuario();
                usuario3.setName("Gisele Campos");
                usuario3.setEmail("camposgiselea@fixit.com");
                usuario3.setPassword(encoder.encode("gisele123"));
                usuario3.setDepartment("Marketing");
                usuario3.setRole("user");

                usuarioRepository.save(admin);
                usuarioRepository.save(tecnico1);
                usuarioRepository.save(tecnico2);
                usuarioRepository.save(usuario1);
                usuarioRepository.save(usuario2);
                usuarioRepository.save(usuario3);

                System.out.println("✅ Usuários criados.");

                // Chamados existentes
                Chamado chamado1 = new Chamado();
                chamado1.setTitulo("Erro no sistema de login");
                chamado1.setDescricao("Não consigo acessar minha conta, aparece erro 500.");
                chamado1.setPrioridade(Prioridade.ALTA);
                chamado1.setStatus(StatusChamado.ABERTO);
                chamado1.setDataCriacao(LocalDateTime.now().minusDays(2));
                chamado1.setUsuario(usuario2);
                chamado1.setTecnico(tecnico1);

                Chamado chamado2 = new Chamado();
                chamado2.setTitulo("Computador não liga");
                chamado2.setDescricao("Meu computador não está ligando desde ontem.");
                chamado2.setPrioridade(Prioridade.MEDIA);
                chamado2.setStatus(StatusChamado.EM_ANDAMENTO);
                chamado2.setDataCriacao(LocalDateTime.now().minusDays(1));
                chamado2.setUsuario(usuario1);
                chamado2.setTecnico(tecnico2);

                Chamado chamado3 = new Chamado();
                chamado3.setTitulo("Solicitação de acesso à VPN");
                chamado3.setDescricao("Preciso acessar a VPN para trabalhar remotamente.");
                chamado3.setPrioridade(Prioridade.BAIXA);
                chamado3.setStatus(StatusChamado.ABERTO);
                chamado3.setDataCriacao(LocalDateTime.now());
                chamado3.setUsuario(usuario1);
                chamado3.setTecnico(null);

                // Chamado crítico em andamento
                Chamado chamadoCritico = new Chamado();
                chamadoCritico.setTitulo("Falha crítica no servidor");
                chamadoCritico.setDescricao("Servidor principal está indisponível desde esta manhã.");
                chamadoCritico.setPrioridade(Prioridade.CRITICA);
                chamadoCritico.setStatus(StatusChamado.EM_ANDAMENTO);
                chamadoCritico.setDataCriacao(LocalDateTime.now().minusHours(5));
                chamadoCritico.setUsuario(usuario2);
                chamadoCritico.setTecnico(tecnico1);

                // Chamados baixos solucionados
                Chamado chamadoBaixo1 = new Chamado();
                chamadoBaixo1.setTitulo("Atualização de software");
                chamadoBaixo1.setDescricao("Solicitação para atualizar o software de edição.");
                chamadoBaixo1.setPrioridade(Prioridade.BAIXA);
                chamadoBaixo1.setStatus(StatusChamado.SOLUCIONADO);
                chamadoBaixo1.setDataCriacao(LocalDateTime.now().minusDays(10));
                chamadoBaixo1.setDataFechamento(LocalDateTime.now().minusDays(5));
                chamadoBaixo1.setUsuario(usuario3);
                chamadoBaixo1.setTecnico(tecnico2);

                Chamado chamadoBaixo2 = new Chamado();
                chamadoBaixo2.setTitulo("Configuração de e-mail");
                chamadoBaixo2.setDescricao("Configuração correta da assinatura no Outlook.");
                chamadoBaixo2.setPrioridade(Prioridade.BAIXA);
                chamadoBaixo2.setStatus(StatusChamado.SOLUCIONADO);
                chamadoBaixo2.setDataCriacao(LocalDateTime.now().minusDays(8));
                chamadoBaixo2.setDataFechamento(LocalDateTime.now().minusDays(3));
                chamadoBaixo2.setUsuario(usuario3);
                chamadoBaixo2.setTecnico(tecnico1);


                // Chamados adicionais para popular o app
Chamado chamado4 = new Chamado();
chamado4.setTitulo("Impressora não conecta à rede");
chamado4.setDescricao("A impressora do setor financeiro não está conectando à rede Wi-Fi.");
chamado4.setPrioridade(Prioridade.MEDIA);
chamado4.setStatus(StatusChamado.SOLUCIONADO);
chamado4.setDataCriacao(LocalDateTime.now().minusWeeks(3));
chamado4.setUsuario(usuario2);
chamado4.setTecnico(null);

Chamado chamado5 = new Chamado();
chamado5.setTitulo("Erro ao salvar documento no sistema");
chamado5.setDescricao("O sistema apresenta erro ao tentar salvar documentos anexados.");
chamado5.setPrioridade(Prioridade.ALTA);
chamado5.setStatus(StatusChamado.SOLUCIONADO);
chamado5.setDataCriacao(LocalDateTime.now().minusWeeks(4).minusDays(2));
chamado5.setUsuario(usuario1);
chamado5.setTecnico(tecnico1);

Chamado chamado6 = new Chamado();
chamado6.setTitulo("Solicitação de novo mouse");
chamado6.setDescricao("O mouse atual está apresentando falhas, solicito substituição.");
chamado6.setPrioridade(Prioridade.BAIXA);
chamado6.setStatus(StatusChamado.SOLUCIONADO);
chamado6.setDataCriacao(LocalDateTime.now().minusMonths(2));
chamado6.setDataFechamento(LocalDateTime.now().minusMonths(1).minusDays(25));
chamado6.setUsuario(usuario3);
chamado6.setTecnico(tecnico2);

Chamado chamado7 = new Chamado();
chamado7.setTitulo("Problemas com acesso ao sistema ERP");
chamado7.setDescricao("Usuário não consegue acessar o sistema ERP após atualização.");
chamado7.setPrioridade(Prioridade.CRITICA);
chamado7.setStatus(StatusChamado.SOLUCIONADO);
chamado7.setDataCriacao(LocalDateTime.now().minusMonths(1).minusDays(10));
chamado7.setDataFechamento(LocalDateTime.now().minusMonths(1));
chamado7.setUsuario(usuario2);
chamado7.setTecnico(tecnico1);

Chamado chamado8 = new Chamado();
chamado8.setTitulo("Solicitação para configurar nova conta de e-mail");
chamado8.setDescricao("Novo colaborador necessita de conta de e-mail com privilégios padrão.");
chamado8.setPrioridade(Prioridade.BAIXA);
chamado8.setStatus(StatusChamado.EM_ANDAMENTO);
chamado8.setDataCriacao(LocalDateTime.now().minusWeeks(6));
chamado8.setUsuario(usuario1);
chamado8.setTecnico(null);

Chamado chamado9 = new Chamado();
chamado9.setTitulo("Computador lento");
chamado9.setDescricao("O computador do usuário está muito lento e travando frequentemente.");
chamado9.setPrioridade(Prioridade.MEDIA);
chamado9.setStatus(StatusChamado.EM_ANDAMENTO);
chamado9.setDataCriacao(LocalDateTime.now().minusWeeks(5));
chamado9.setUsuario(usuario3);
chamado9.setTecnico(tecnico2);

// Salvar os chamados adicionais
chamadoRepository.save(chamado1);
chamadoRepository.save(chamado2);
chamadoRepository.save(chamado3);
chamadoRepository.save(chamadoCritico);
chamadoRepository.save(chamadoBaixo1);
chamadoRepository.save(chamadoBaixo2);
chamadoRepository.save(chamado4);
chamadoRepository.save(chamado5);
chamadoRepository.save(chamado6);
chamadoRepository.save(chamado7);
chamadoRepository.save(chamado8);
chamadoRepository.save(chamado9);


                System.out.println("✅ Chamados criados.");
            }
        };
    }
}
