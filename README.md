# App para monitorar gravação da rádio Verdes Mares AM

App para Android desenvolvido em Java utilizando um Web Service em PHP, criado para facilitar o monitoramento dos arquivos de áudio gravados, assim como ser avisado via notificação no caso da falha de comunicação com a servidor ou por algum motivo o software tenha parado de gerar os arquivos.
A lógica foi desenvolvida em cima das datas e tamanho do arquivo, o app chama o web service que lê os arquivos do diretório da data atual, pegando o último e retornando os dados, o java compara os dados recebidos com os guardados anteriormente e atualiza as informações na tela, caso conste que está possivelmente parado, altera cor e texto.
Alerta através de notificação e email em implementação.
