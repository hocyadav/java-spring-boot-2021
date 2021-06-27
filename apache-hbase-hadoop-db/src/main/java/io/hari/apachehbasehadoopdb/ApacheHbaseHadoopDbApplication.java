package io.hari.apachehbasehadoopdb;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * https://www.baeldung.com/hbase
 */
@SpringBootApplication
public class ApacheHbaseHadoopDbApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ApacheHbaseHadoopDbApplication.class, args);
	}

	/**
	 * 1. create configuration
	 * 2. create hbase-site.xml and connect hbase + zookeeper -then-> check hbase master status - available()
	 * 3. create table instance, column -then-> create table descriptor instance using table instance , & then add column family to it
	 * 4. create connection instance and admin instance
	 * 5. using admin instance create table
	 */
	@Override
	public void run(String... args) throws Exception {
		// 1. create configuration
		Configuration config = HBaseConfiguration.create();

		// 2. create xml and connect hbase + zookeeper -then-> check hbase master status - available()
		String path = this.getClass()
				.getClassLoader()
				.getResource("hbase-site.xml")
				.getPath();
		config.addResource(new Path(path));

		HBaseAdmin.available(config);

		//3. create table instance, column -then-> create table descriptor instance using table instance , & then add column family to it
		TableName table1 = TableName.valueOf("Table2a");
		String family1 = "Family1";
		String family2 = "Family2";
		HTableDescriptor desc = new HTableDescriptor(table1);
		desc.addFamily(new HColumnDescriptor(family1));
		desc.addFamily(new HColumnDescriptor(family2));

		//4. create connection instance and admin instance
		Connection connection = ConnectionFactory.createConnection(config);
		Admin admin = connection.getAdmin();

		//5. using admin instance create table
		admin.createTable(desc);
	}
}
