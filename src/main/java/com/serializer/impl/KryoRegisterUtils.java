package com.serializer.impl;

import com.esotericsoftware.kryo.Kryo;
import com.serializer.raft.DefaultEventType;
import com.serializer.raft.DefaultOperationId;
import com.serializer.raft.MemberId;
import com.serializer.raft.OperationType;
import com.serializer.raft.PrimitiveEvent;
import com.serializer.raft.PrimitiveOperation;
import com.serializer.raft.RaftError;
import com.serializer.raft.RaftMember;
import com.serializer.raft.ReadConsistency;
import com.serializer.raft.SessionMetadata;
import com.serializer.raft.log.CloseSessionEntry;
import com.serializer.raft.log.CommandEntry;
import com.serializer.raft.log.ConfigurationEntry;
import com.serializer.raft.log.InitializeEntry;
import com.serializer.raft.log.KeepAliveEntry;
import com.serializer.raft.log.MetadataEntry;
import com.serializer.raft.log.OpenSessionEntry;
import com.serializer.raft.log.QueryEntry;
import com.serializer.raft.request.AppendRequest;
import com.serializer.raft.request.CloseSessionRequest;
import com.serializer.raft.request.CommandRequest;
import com.serializer.raft.request.ConfigureRequest;
import com.serializer.raft.request.InstallRequest;
import com.serializer.raft.request.JoinRequest;
import com.serializer.raft.request.KeepAliveRequest;
import com.serializer.raft.request.LeaveRequest;
import com.serializer.raft.request.MetadataRequest;
import com.serializer.raft.request.OpenSessionRequest;
import com.serializer.raft.request.PollRequest;
import com.serializer.raft.request.PublishRequest;
import com.serializer.raft.request.QueryRequest;
import com.serializer.raft.request.ReconfigureRequest;
import com.serializer.raft.request.ResetRequest;
import com.serializer.raft.request.TransferRequest;
import com.serializer.raft.request.VoteRequest;
import com.serializer.raft.response.AppendResponse;
import com.serializer.raft.response.CloseSessionResponse;
import com.serializer.raft.response.CommandResponse;
import com.serializer.raft.response.ConfigureResponse;
import com.serializer.raft.response.InstallResponse;
import com.serializer.raft.response.JoinResponse;
import com.serializer.raft.response.KeepAliveResponse;
import com.serializer.raft.response.LeaveResponse;
import com.serializer.raft.response.MetadataResponse;
import com.serializer.raft.response.OpenSessionResponse;
import com.serializer.raft.response.PollResponse;
import com.serializer.raft.response.QueryResponse;
import com.serializer.raft.response.RaftResponse;
import com.serializer.raft.response.ReconfigureResponse;
import com.serializer.raft.response.TransferResponse;
import com.serializer.raft.response.VoteResponse;

public class KryoRegisterUtils {
	/**
	 * Kryo registration Id for user custom registration.
	 */
	private static final int BEGIN_USER_CUSTOM_ID = 500;
	
	
	public static void register(Kryo kryo) {
		kryo.setRegistrationRequired(true);
    	int id = BEGIN_USER_CUSTOM_ID;
        kryo.register(byte[].class, id++);
        kryo.register(java.util.ArrayList.class, id++);
        kryo.register(java.util.HashMap.class, id++);
        kryo.register(java.util.HashSet.class, id++);
        kryo.register(long[].class, id++);
        kryo.register(OpenSessionRequest.class, id++);
        kryo.register(ReadConsistency.class, id++);
        kryo.register(AppendRequest.class, id++);
        kryo.register(ConfigurationEntry.class, id++);
        kryo.register(RaftMember.class, id++);
        kryo.register(RaftMember.Type.class, id++);
        kryo.register(MemberId.class, id++);
        kryo.register(InitializeEntry.class, id++);
        kryo.register(KeepAliveEntry.class, id++);
        kryo.register(OpenSessionEntry.class, id++);
        kryo.register(CloseSessionEntry.class, id++);
        kryo.register(MetadataEntry.class, id++);
        kryo.register(CommandEntry.class, id++);
        kryo.register(DefaultOperationId.class, id++);
        kryo.register(PrimitiveOperation.class, id++);
        kryo.register(QueryEntry.class, id++);
        kryo.register(CloseSessionRequest.class, id++);
        kryo.register(OperationType.class, id++);
        kryo.register(CommandRequest.class, id++);
        kryo.register(ConfigureRequest.class, id++);
        kryo.register(InstallRequest.class, id++);
        kryo.register(JoinRequest.class, id++);
        kryo.register(KeepAliveRequest.class, id++);
        kryo.register(LeaveRequest.class, id++);
        kryo.register(MetadataRequest.class, id++);
        kryo.register(PollRequest.class, id++);
        kryo.register(PublishRequest.class, id++);
        kryo.register(PrimitiveEvent.class, id++);
        kryo.register(DefaultEventType.class, id++);
        kryo.register(QueryRequest.class, id++);
        kryo.register(ReconfigureRequest.class, id++);
        kryo.register(ResetRequest.class, id++);
        kryo.register(TransferRequest.class, id++);
        kryo.register(VoteRequest.class, id++);
        
        kryo.register(OpenSessionResponse.class, id++);
        kryo.register(AppendResponse.class, id++);
        kryo.register(CloseSessionResponse.class, id++);
        kryo.register(CommandResponse.class, id++);
        kryo.register(ConfigureResponse.class, id++);
        kryo.register(InstallResponse.class, id++);
        kryo.register(JoinResponse.class, id++);
        kryo.register(KeepAliveResponse.class, id++);
        kryo.register(LeaveResponse.class, id++);
        
        kryo.register(MetadataResponse.class, id++);
        kryo.register(PollResponse.class, id++);
        kryo.register(QueryResponse.class, id++);
        kryo.register(ReconfigureResponse.class, id++);
        kryo.register(TransferResponse.class, id++);
        kryo.register(VoteResponse.class, id++);
        
        kryo.register(RaftError.class, id++);
        kryo.register(RaftError.Type.class, id++);
        kryo.register(RaftResponse.Status.class, id++);
        kryo.register(SessionMetadata.class, id++);
	}
}
