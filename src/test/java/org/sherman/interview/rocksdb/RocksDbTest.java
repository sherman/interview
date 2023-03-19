package org.sherman.interview.rocksdb;

import java.util.ArrayList;
import java.util.Arrays;
import org.rocksdb.ColumnFamilyDescriptor;
import org.rocksdb.ColumnFamilyHandle;
import org.rocksdb.ColumnFamilyOptions;
import org.rocksdb.DBOptions;
import org.rocksdb.MemTableConfig;
import org.rocksdb.Options;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;
import org.rocksdb.SkipListMemTableConfig;
import org.rocksdb.WriteBatch;
import org.rocksdb.WriteOptions;
import org.testng.annotations.Test;

public class RocksDbTest {
    static {
        RocksDB.loadLibrary();
    }

    @Test
    public void writeData() throws RocksDBException {
        try (var options = new Options()
            .setCreateIfMissing(true)
            .setCreateMissingColumnFamilies(true)
            .setWriteBufferSize(268435456)
            .setPersistStatsToDisk(false)
            .setMemTableConfig(new SkipListMemTableConfig());
             var db = RocksDB.open(options, "/tmp/rocksdb-data")
        ) {
            try {
                for (var i = 0; i < 1000; i++) {
                    try (var batch = new WriteBatch()) {
                        for (var j = 0; j < 1000; j++) {
                            batch.put(String.valueOf(i + j).getBytes(), String.valueOf(i + j).getBytes());
                        }
                        var opts = new WriteOptions();
                        opts.setDisableWAL(true);
                        db.write(opts, batch);
                    }
                }
            } finally {

            }
        }
    }
}
