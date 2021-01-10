package com.application;

import com.serializer.raft.creator.RaftRequestCreator;
import com.serializer.raft.creator.RaftResponseCreator;
import com.serializer.raft.creator.RequestResponseCreator;
import com.serializer.raft.creator.TestSerializerCreatorFactory;

public class TestUtils {
    static RequestResponseCreator.OpenSessionRequestCreator osrequest = RequestResponseCreator.OpenSessionRequestCreator.INSTANCE;
    static RequestResponseCreator.AppendRequestCreator arequest = RequestResponseCreator.AppendRequestCreator.INSTANCE;
    static RequestResponseCreator.CloseSessionRequestCreator csrequest = RequestResponseCreator.CloseSessionRequestCreator.INSTANCE;
    static RequestResponseCreator.CommandRequestCreator crequest = RequestResponseCreator.CommandRequestCreator.INSTANCE;
    static RequestResponseCreator.ConfigureRequestCreator cfrequest = RequestResponseCreator.ConfigureRequestCreator.INSTANCE;
    static RequestResponseCreator.InstallRequestCreator irequest = RequestResponseCreator.InstallRequestCreator.INSTANCE;
    static RequestResponseCreator.JoinRequestCreator jrequest = RequestResponseCreator.JoinRequestCreator.INSTANCE;
    static RequestResponseCreator.KeepAliveRequestCreator karequest = RequestResponseCreator.KeepAliveRequestCreator.INSTANCE;
    static RequestResponseCreator.LeaveRequestCreator lrequest = RequestResponseCreator.LeaveRequestCreator.INSTANCE;
    static RequestResponseCreator.MetadataRequestCreator mrequest = RequestResponseCreator.MetadataRequestCreator.INSTANCE;
    static RequestResponseCreator.PollRequestCreator prequest = RequestResponseCreator.PollRequestCreator.INSTANCE;
    static RequestResponseCreator.PublishRequestCreator plrequest = RequestResponseCreator.PublishRequestCreator.INSTANCE;
    static RequestResponseCreator.QueryRequestCreator qrequest = RequestResponseCreator.QueryRequestCreator.INSTANCE;
    static RequestResponseCreator.ReconfigureRequestCreator rcrequest = RequestResponseCreator.ReconfigureRequestCreator.INSTANCE;
    static RequestResponseCreator.ResetRequestCreator resrequest = RequestResponseCreator.ResetRequestCreator.INSTANCE;
    static RequestResponseCreator.TransferRequestCreator trequest = RequestResponseCreator.TransferRequestCreator.INSTANCE;
    static RequestResponseCreator.VoteRequestCreator vrequest = RequestResponseCreator.VoteRequestCreator.INSTANCE;
    static RaftRequestCreator[] requestCreators = new RaftRequestCreator[] { osrequest, arequest, csrequest, crequest,
            cfrequest, irequest, jrequest, karequest, lrequest, mrequest, prequest, plrequest, qrequest, rcrequest,
            resrequest, trequest, vrequest };

    static RequestResponseCreator.OpenSessionResponseCreator osresponse = RequestResponseCreator.OpenSessionResponseCreator.INSTANCE;
    static RequestResponseCreator.AppendResponseCreator aresponse = RequestResponseCreator.AppendResponseCreator.INSTANCE;
    static RequestResponseCreator.CloseSessionResponseCreator csresponse = RequestResponseCreator.CloseSessionResponseCreator.INSTANCE;
    static RequestResponseCreator.CommandResponseCreator cresponce = RequestResponseCreator.CommandResponseCreator.INSTANCE;
    static RequestResponseCreator.ConfigureResponseCreator cfresponse = RequestResponseCreator.ConfigureResponseCreator.INSTANCE;
    static RequestResponseCreator.InstallResponseCreator iresponse = RequestResponseCreator.InstallResponseCreator.INSTANCE;
    static RequestResponseCreator.JoinResponseCreator jresponse = RequestResponseCreator.JoinResponseCreator.INSTANCE;
    static RequestResponseCreator.KeepAliveResponseCreator karesponse = RequestResponseCreator.KeepAliveResponseCreator.INSTANCE;
    static RequestResponseCreator.LeaveResponseCreator lresponse = RequestResponseCreator.LeaveResponseCreator.INSTANCE;
    static RequestResponseCreator.MetadataResponseCreator mresponse = RequestResponseCreator.MetadataResponseCreator.INSTANCE;
    static RequestResponseCreator.PollResponseCreator presponse = RequestResponseCreator.PollResponseCreator.INSTANCE;
    static RequestResponseCreator.QueryResponseCreator qresponse = RequestResponseCreator.QueryResponseCreator.INSTANCE;
    static RequestResponseCreator.ReconfigureResponseCreator rcresponse = RequestResponseCreator.ReconfigureResponseCreator.INSTANCE;
    static RequestResponseCreator.TransferResponseCreator tresponse = RequestResponseCreator.TransferResponseCreator.INSTANCE;
    static RequestResponseCreator.VoteResponseCreator vresponse = RequestResponseCreator.VoteResponseCreator.INSTANCE;
    static RaftResponseCreator[] responseCreators = new RaftResponseCreator[] {osresponse, aresponse, csresponse,
            cresponce, cfresponse, iresponse, jresponse, karesponse, lresponse, mresponse, presponse,
            qresponse, rcresponse, tresponse, vresponse};

    static TestSerializerCreatorFactory.JavaSerializerCreator javaSerializerCreator = TestSerializerCreatorFactory.JavaSerializerCreator.INSTANCE;
    static TestSerializerCreatorFactory.HessianSerializerCreator hessianSerializerCreator = TestSerializerCreatorFactory.HessianSerializerCreator.INSTANCE;
    static TestSerializerCreatorFactory.KryoSerializerCreator kryoNotPreRegisterClassCreator = TestSerializerCreatorFactory.KryoSerializerCreator.INSTANCE;
    static TestSerializerCreatorFactory.kryoSerializerNotWriteClassCreator kryoPreRegisterClassCreator = TestSerializerCreatorFactory.kryoSerializerNotWriteClassCreator.INSTANCE;
    static TestSerializerCreatorFactory.ProtostuffSerializerCreator protostuffSerializerCreator = TestSerializerCreatorFactory.ProtostuffSerializerCreator.INSTANCE;
}
