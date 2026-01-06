# Banco de dados

Foi utilizado o banco H2, para subir deve adicionar a configuração abaixo no standalone.xml

<datasources>
	<datasource jta="true" jndi-name="java:/datasources/BeneficioDS" pool-name="BeneficioDS" enabled="true" use-java-context="true">
		<connection-url>jdbc:h2:file:${jboss.server.data.dir}/beneficio</connection-url>
		<driver>h2</driver>
		<security>
			<user-name>sa</user-name>
			<password>sa</password>
		</security>
	</datasource>
	<drivers>
		<driver name="h2" module="com.h2database.h2">
			<xa-datasource-class>org.h2.jdbcx.JdbcDataSource</xa-datasource-class>
		</driver>
	</drivers>
</datasources>

# Servidor para subir a aplicação
Foi utilizado o widfly26

utilizando o eclipse, na aba server, clique com o botão direito do mouse new -> server, adicione o widfly 24+ ou 26 e clque em Ok


# Teste Unitário

Foi utilizado o moquito para mocar as informações e o JUNIT4 


# Correção do BUG

A correção ocorreu na classe BeneficioEjbService, fiz as validações de saldo, caso tenha saldo negativo o sistema exibe a mensagem Saldo insuficiente para a operação
Se os dados da conta não existir no banco o sistema exibe a mensagem Conta de origem não encontrada ou Conta de destino não encontrada,
Se ocorrer algum erro na tranferencia, o sistema faz o rolback e não faz a transação, caso a tranferência seja realizada o sitema atualiza os saldos das contas envolvidas na transação.
