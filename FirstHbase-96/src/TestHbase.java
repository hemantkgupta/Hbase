import java.io.IOException;
import java.io.InterruptedIOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.RetriesExhaustedWithDetailsException;
import org.apache.hadoop.hbase.util.Bytes;

public class TestHbase {

	public static void main(String[] args) {
		// The code assume a tbale created already by command:
		// create 'firsttable', 'colf1'

		Configuration conf = HBaseConfiguration.create();
		HTable table = null;

		try {
			table = new HTable(conf, "firsttable");
		} catch (IOException e) {
			e.printStackTrace();
		}

		byte[] row1 = Bytes.toBytes("row1");
		Put p1 = new Put(row1);
		
		byte[] databytes = Bytes.toBytes("colf1");
		p1.add(databytes, Bytes.toBytes("qual1"), Bytes.toBytes("valu1"));

		try {
			table.put(p1);
		} catch (RetriesExhaustedWithDetailsException e) {
			e.printStackTrace();
		} catch (InterruptedIOException e) {
			e.printStackTrace();
		}

	}

}
