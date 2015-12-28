/**
 * Created by An on 28.12.2015.
 */
import java.net.*;
import java.io.*;
import net.wimpi.modbus.*;
import net.wimpi.modbus.msg.*;
import net.wimpi.modbus.io.*;
import net.wimpi.modbus.net.*;
import net.wimpi.modbus.procimg.Register;
import net.wimpi.modbus.util.*;


public class TestModbus {

    public static void main(String[] args) throws Exception {
             /* The important instances of the classes mentioned before */
        SerialConnection con = null; //the connection
        // ModbuSerialTransaction trans = null; //the transaction

        ModbusSerialTransaction trans = null;

        ReadInputRegistersRequest req = null; //the request
        ReadInputRegistersResponse res = null; //the response

        /* Variables for storing the parameters */
        String portname = null; //the name of the serial port to be used
        int unitid = 2; //the unit identifier we will be talking to
        int ref = 0; //the reference, where to start reading from
        int count = 7; //the count of IR's to read
        int repeat = 1; //a loop for repeating the transaction

        portname = "COM4";

        //2. Set master identifier
        //  ModbusCoupler.createModbusCoupler(null);
        ModbusCoupler.getReference().setUnitID(1);

        //3. Setup serial parameters
        SerialParameters params = new SerialParameters();
        params.setPortName(portname);
        params.setBaudRate(19200);
        params.setDatabits(8);
        params.setParity("None");
        params.setStopbits(1);
        params.setEncoding("rtu");
        params.setEcho(false);


        //4. Open the connection
        con = new SerialConnection(params);
        con.open();

        //5. Prepare a request
        //req = new ReadInputRegistersRequest(ref, count);
        ReadCoilsRequest reqCoils = new ReadCoilsRequest(0, 8);
        reqCoils.setUnitID(2);
        reqCoils.setHeadless();

        //6. Prepare a transaction
        trans = new ModbusSerialTransaction(con);
        trans.setRequest(reqCoils);

        //7. Execute the transaction repeat times
        ReadCoilsResponse ress = null;

        trans.execute();
        ress = (ReadCoilsResponse) trans.getResponse();
        System.out.println("ALL COINS " + ress.getCoils().toString());
        System.out.println("WHAT " + ress.getCoilStatus(7));


        // TEST WRITE COINS
        BitVector bit = new BitVector(8);
        bit.setBit(1, true);
        bit.setBit(3, true);

        WriteMultipleCoilsRequest wrreq = new WriteMultipleCoilsRequest(0, bit);
        wrreq.setUnitID(2);
        wrreq.setHeadless();

        trans.setRequest(wrreq);
        trans.execute();


        //8. Close the connection

        con.close();


        System.out.println("WELL DONE");
    }
}