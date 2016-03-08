- Preparar tela para cadastrar patologias e papear patologias, indexando todas;
- Verificar estrutura dos laudos importados;
- Preparar tela para indexação de Laudos;
- Preparar tela de buscas;
- Adicionar filtro por modalidades na tela de buscas;
- Preparar tela de buscas em formato de gráfos;
- Usar isso: http://www.graphdracula.net/ ;

# know-rad

A project for apply knowledge discovery in radiologic notes.

# Solr
Commands (walk to solr-5.4.0\bin with your terminal/command line):
solr start </br>
solr create -c laudos (create a new core)</br>
solr status</br>
solr stop -all</br>

Obs:</br>

Após indexar, ir até o diretório solr-5.4.0\server\solr\laudos\conf e abrir o arquivo managed-schema
localizar os campos indexados (ctrl + F idPaciente, por exemplo) 
e ajustar o tipo de cada um para o singular (longs para long, strings para string, etc)
parar o solr (solr stop -all) e iniciar (solr start).
Realizar nova query e verificar se o JSON retornado está no formato correto.

...
