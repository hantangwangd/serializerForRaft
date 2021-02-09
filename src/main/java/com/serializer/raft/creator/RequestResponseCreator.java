package com.serializer.raft.creator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import com.serializer.raft.EventType;
import com.serializer.raft.MemberId;
import com.serializer.raft.OperationId;
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
import com.serializer.raft.log.RaftLogEntry;
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
import com.serializer.raft.request.RaftRequest;
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

public class RequestResponseCreator {
	
	public static class AppendRequestCreator implements RaftRequestCreator {
		public static final AppendRequestCreator INSTANCE = new AppendRequestCreator();
		@Override
		public RaftRequest create() {
			return RequestResponseCreator.createAppendRequest();
		}
    	
    }
	
	public static class CloseSessionRequestCreator implements RaftRequestCreator {
		public static final CloseSessionRequestCreator INSTANCE = new CloseSessionRequestCreator();
		@Override
		public RaftRequest create() {
			return RequestResponseCreator.createCloseSessionRequest();
		}
	}
	
	public static class CommandRequestCreator implements RaftRequestCreator {
		public static final CommandRequestCreator INSTANCE = new CommandRequestCreator();
		@Override
		public RaftRequest create() {
			return RequestResponseCreator.createCommandRequest();
		}
	}
	
	public static class ConfigureRequestCreator implements RaftRequestCreator {
		public static final ConfigureRequestCreator INSTANCE = new ConfigureRequestCreator();
		@Override
		public RaftRequest create() {
			return RequestResponseCreator.createConfigureRequest();
		}
	}
	
	public static class InstallRequestCreator implements RaftRequestCreator {
		public static final InstallRequestCreator INSTANCE = new InstallRequestCreator();
		@Override
		public RaftRequest create() {
			return RequestResponseCreator.createInstallRequest();
		}
	}
	
	public static class JoinRequestCreator implements RaftRequestCreator {
		public static final JoinRequestCreator INSTANCE = new JoinRequestCreator();
		@Override
		public RaftRequest create() {
			return RequestResponseCreator.createJoinRequest();
		}
	}
	
	public static class KeepAliveRequestCreator implements RaftRequestCreator {
		public static final KeepAliveRequestCreator INSTANCE = new KeepAliveRequestCreator();
		@Override
		public RaftRequest create() {
			return RequestResponseCreator.createKeepAliveRequest();
		}
	}
	
	public static class LeaveRequestCreator implements RaftRequestCreator {
		public static final LeaveRequestCreator INSTANCE = new LeaveRequestCreator();
		@Override
		public RaftRequest create() {
			return RequestResponseCreator.createLeaveRequest();
		}
	}
	
	public static class MetadataRequestCreator implements RaftRequestCreator {
		public static final MetadataRequestCreator INSTANCE = new MetadataRequestCreator();
		@Override
		public RaftRequest create() {
			return RequestResponseCreator.createMetadataRequest();
		}
	}
	
	public static class OpenSessionRequestCreator implements RaftRequestCreator {
		public static final OpenSessionRequestCreator INSTANCE = new OpenSessionRequestCreator();
		@Override
		public RaftRequest create() {
			return RequestResponseCreator.createOpenSessionRequest();
		}
	}
	
	public static class PollRequestCreator implements RaftRequestCreator {
		public static final PollRequestCreator INSTANCE = new PollRequestCreator();
		@Override
		public RaftRequest create() {
			return RequestResponseCreator.createPollRequest();
		}
	}
	
	public static class PublishRequestCreator implements RaftRequestCreator {
		public static final PublishRequestCreator INSTANCE = new PublishRequestCreator();
		@Override
		public RaftRequest create() {
			return RequestResponseCreator.createPublishRequest();
		}
	}
	
	public static class QueryRequestCreator implements RaftRequestCreator {
		public static final QueryRequestCreator INSTANCE = new QueryRequestCreator();
		@Override
		public RaftRequest create() {
			return RequestResponseCreator.createQueryRequest();
		}
	}
	
	public static class ReconfigureRequestCreator implements RaftRequestCreator {
		public static final ReconfigureRequestCreator INSTANCE = new ReconfigureRequestCreator();
		@Override
		public RaftRequest create() {
			return RequestResponseCreator.createReconfigureRequest();
		}
	}
	
	public static class ResetRequestCreator implements RaftRequestCreator {
		public static final ResetRequestCreator INSTANCE = new ResetRequestCreator();
		@Override
		public RaftRequest create() {
			return RequestResponseCreator.createResetRequest();
		}
	}
	
	public static class TransferRequestCreator implements RaftRequestCreator {
		public static final TransferRequestCreator INSTANCE = new TransferRequestCreator();
		@Override
		public RaftRequest create() {
			return RequestResponseCreator.createTransferRequest();
		}
	}
	
	public static class VoteRequestCreator implements RaftRequestCreator {
		public static final VoteRequestCreator INSTANCE = new VoteRequestCreator();
		@Override
		public RaftRequest create() {
			return RequestResponseCreator.createVoteRequest();
		}
	}
	
	static Random ran = new Random();
	private static AppendRequest createAppendRequest() {
		List<RaftLogEntry> entries = new ArrayList<>();
		entries.add(new OpenSessionEntry(0, System.currentTimeMillis(), MemberId.anonymous().id(), 
				UUID.randomUUID().toString(), UUID.randomUUID().toString(), 
				("this is a test service config" + UUID.randomUUID().toString()).getBytes(), 
				ReadConsistency.LINEARIZABLE, ran.nextInt(500), ran.nextInt(1000000)));
		
		long[] sessionIds = new long[] {ran.nextInt(100000), ran.nextInt(100000), ran.nextInt(100000), ran.nextInt(100000)};
		long[] commandSequences = new long[] {ran.nextInt(100000), ran.nextInt(100000), ran.nextInt(100000), ran.nextInt(100000)};
		long[] eventIndexes = new long[] {ran.nextInt(100000), ran.nextInt(100000), ran.nextInt(100000), ran.nextInt(100000)};
		entries.add(new KeepAliveEntry(ran.nextInt(100000), System.currentTimeMillis(), sessionIds, commandSequences, eventIndexes));
		
		ArrayList<RaftMember> members = new ArrayList<>();
		members.add(new RaftMember(MemberId.from(UUID.randomUUID().toString()), RaftMember.Type.ACTIVE, System.currentTimeMillis()));
		members.add(new RaftMember(MemberId.from(UUID.randomUUID().toString()), RaftMember.Type.ACTIVE, System.currentTimeMillis()));
		members.add(new RaftMember(MemberId.from(UUID.randomUUID().toString()), RaftMember.Type.ACTIVE, System.currentTimeMillis()));
		entries.add(new ConfigurationEntry(ran.nextInt(100000), System.currentTimeMillis(), members));
		
		entries.add(new MetadataEntry(ran.nextInt(100000), System.currentTimeMillis(), ran.nextInt(100000)));
		
		entries.add(new ConfigurationEntry(ran.nextInt(100000), System.currentTimeMillis(), members));
		
		entries.add(new InitializeEntry(ran.nextInt(100000), System.currentTimeMillis()));
		
		entries.add(new CommandEntry(ran.nextInt(100000), System.currentTimeMillis(), ran.nextInt(100000), ran.nextInt(100000), 
				PrimitiveOperation.operation(OperationId.command(UUID.randomUUID().toString()), 
						("this is test command1" + UUID.randomUUID()).toString().getBytes())));
		
		entries.add(new CommandEntry(ran.nextInt(100000), System.currentTimeMillis(), ran.nextInt(100000), ran.nextInt(100000), 
				PrimitiveOperation.operation(OperationId.command(UUID.randomUUID().toString()), 
						("this is test command2" + UUID.randomUUID()).toString().getBytes())));
		
		entries.add(new CommandEntry(ran.nextInt(100000), System.currentTimeMillis(), ran.nextInt(100000), ran.nextInt(100000), 
				PrimitiveOperation.operation(OperationId.command(UUID.randomUUID().toString()), 
						("this is test command3" + UUID.randomUUID()).toString().getBytes())));
		
		entries.add(new CommandEntry(ran.nextInt(100000), System.currentTimeMillis(), ran.nextInt(100000), ran.nextInt(100000), 
				PrimitiveOperation.operation(OperationId.command(UUID.randomUUID().toString()), 
						("this is test command4" + UUID.randomUUID()).toString().getBytes())));
		
		entries.add(new QueryEntry(ran.nextInt(100000), System.currentTimeMillis(), ran.nextInt(100000), ran.nextInt(100000), 
				PrimitiveOperation.operation(OperationId.query(UUID.randomUUID().toString()), 
						("this is test query1" + UUID.randomUUID()).toString().getBytes())));
		
		entries.add(new QueryEntry(ran.nextInt(100000), System.currentTimeMillis(), ran.nextInt(100000), ran.nextInt(100000), 
				PrimitiveOperation.operation(OperationId.query(UUID.randomUUID().toString()), 
						("this is test query2" + UUID.randomUUID().toString()).getBytes())));
		
		entries.add(new QueryEntry(ran.nextInt(100000), System.currentTimeMillis(), ran.nextInt(100000), ran.nextInt(100000), 
				PrimitiveOperation.operation(OperationId.query(UUID.randomUUID().toString()), 
						("this is test query3" + UUID.randomUUID().toString()).getBytes())));
		
		entries.add(new QueryEntry(ran.nextInt(100000), System.currentTimeMillis(), ran.nextInt(100000), ran.nextInt(100000), 
				PrimitiveOperation.operation(OperationId.query(UUID.randomUUID().toString()), 
						("this is test query4" + UUID.randomUUID().toString()).getBytes())));
		
		entries.add(new CloseSessionEntry(ran.nextInt(100000), System.currentTimeMillis(), ran.nextInt(100000), true, false));
		
		AppendRequest request = new AppendRequest(ran.nextInt(100000), UUID.randomUUID().toString(), 
				ran.nextInt(100000), ran.nextInt(100000), entries, ran.nextInt(100000));
		
		return request;
	}
	
	private static CloseSessionRequest createCloseSessionRequest() {
		CloseSessionRequest request = new CloseSessionRequest(ran.nextInt(100000), false);
		return request;
	}
	
	private static CommandRequest createCommandRequest() {
		CommandRequest request = new CommandRequest(ran.nextInt(100000), ran.nextInt(100000), 
				PrimitiveOperation.operation(OperationId.command(UUID.randomUUID().toString()), 
						("this is test command4" + UUID.randomUUID().toString()).getBytes()));
		return request;
	}
	
	private static ConfigureRequest createConfigureRequest() {
		ArrayList<RaftMember> members = new ArrayList<>();
		members.add(new RaftMember(MemberId.from(UUID.randomUUID().toString()), RaftMember.Type.ACTIVE, System.currentTimeMillis()));
		members.add(new RaftMember(MemberId.from(UUID.randomUUID().toString()), RaftMember.Type.ACTIVE, System.currentTimeMillis()));
		members.add(new RaftMember(MemberId.from(UUID.randomUUID().toString()), RaftMember.Type.ACTIVE, System.currentTimeMillis()));
		ConfigureRequest request = new ConfigureRequest(ran.nextInt(100000), UUID.randomUUID().toString(), ran.nextInt(100000), 
				System.currentTimeMillis(), members);
		return request;
	}
	
	private static InstallRequest createInstallRequest() {
		byte[] bs = new byte[31500];
		Arrays.fill(bs, (byte)0);
		InstallRequest request = new InstallRequest(ran.nextInt(100000), MemberId.from(UUID.randomUUID().toString()), 
				ran.nextInt(100000), System.currentTimeMillis(), ran.nextInt(100000), ran.nextInt(100000), bs , true);
		return request;
	}
	
	private static JoinRequest createJoinRequest() {
		JoinRequest request = new JoinRequest(new RaftMember(MemberId.from(UUID.randomUUID().toString()), 
				RaftMember.Type.ACTIVE, System.currentTimeMillis()));
		return request;
	}
	
	private static KeepAliveRequest createKeepAliveRequest() {
		long[] sessionIds = new long[] {ran.nextInt(100000), ran.nextInt(100000), ran.nextInt(100000), ran.nextInt(100000)};
		long[] commandSequences = new long[] {ran.nextInt(100000), ran.nextInt(100000), ran.nextInt(100000), ran.nextInt(100000)};
		long[] eventIndexes = new long[] {ran.nextInt(100000), ran.nextInt(100000), ran.nextInt(100000), ran.nextInt(100000)};
		KeepAliveRequest request = new KeepAliveRequest(sessionIds, commandSequences, eventIndexes);
		return request;
	}
	
	private static LeaveRequest createLeaveRequest() {
		LeaveRequest request = new LeaveRequest(new RaftMember(MemberId.from(UUID.randomUUID().toString()), 
				RaftMember.Type.ACTIVE, System.currentTimeMillis()));
		return request;
	}
	
	private static MetadataRequest createMetadataRequest() {
		MetadataRequest request = new MetadataRequest(ran.nextInt(100000));
		return request;
	}
	
	private static OpenSessionRequest createOpenSessionRequest() {
		OpenSessionRequest request = new OpenSessionRequest(UUID.randomUUID().toString(), UUID.randomUUID().toString(), 
				UUID.randomUUID().toString(), 
				("this is a test service config" + UUID.randomUUID().toString()).getBytes(), 
				ReadConsistency.LINEARIZABLE, ran.nextInt(100000), ran.nextInt(100000));
		return request;
	}
	
	private static PollRequest createPollRequest() {
		PollRequest request = new PollRequest(ran.nextInt(100000), UUID.randomUUID().toString(), 
				ran.nextInt(100000), ran.nextInt(100000));
		return request;
	}
	
	private static PublishRequest createPublishRequest() {
		List<PrimitiveEvent> events = new ArrayList<>();
		events.add(new PrimitiveEvent(EventType.from(UUID.randomUUID().toString()), 
				("this is test primitive event1" + UUID.randomUUID().toString()).getBytes()));
		events.add(new PrimitiveEvent(EventType.from(UUID.randomUUID().toString()), 
				("this is test primitive event2" + UUID.randomUUID().toString()).getBytes()));
		events.add(new PrimitiveEvent(EventType.from(UUID.randomUUID().toString()), 
				("this is test primitive event3" + UUID.randomUUID().toString()).getBytes()));
		events.add(new PrimitiveEvent(EventType.from(UUID.randomUUID().toString()), 
				("this is test primitive event4" + UUID.randomUUID().toString()).getBytes()));
		PublishRequest request = new PublishRequest(ran.nextInt(100000), ran.nextInt(100000), ran.nextInt(100000), events);
		return request;
	}
	
	private static QueryRequest createQueryRequest() {
		QueryRequest request = new QueryRequest(ran.nextInt(100000), ran.nextInt(100000), 
				PrimitiveOperation.operation(OperationId.query(UUID.randomUUID().toString()), 
				("this is test query4" + UUID.randomUUID().toString()).getBytes()), ran.nextInt(100000));
		return request;
	}
	
	private static ReconfigureRequest createReconfigureRequest() {
		ReconfigureRequest request = new ReconfigureRequest(new RaftMember(MemberId.from(UUID.randomUUID().toString()), 
				RaftMember.Type.ACTIVE, System.currentTimeMillis()), ran.nextInt(100000), ran.nextInt(100000));
		return request;
	}
	
	private static ResetRequest createResetRequest() {
		ResetRequest request = new ResetRequest(ran.nextInt(100000), ran.nextInt(100000));
		return request;
	}
	
	private static TransferRequest createTransferRequest() {
		TransferRequest request = new TransferRequest(MemberId.from(UUID.randomUUID().toString()));
		return request;
	}
	
	private static VoteRequest createVoteRequest() {
		VoteRequest request = new VoteRequest(0, UUID.randomUUID().toString(), ran.nextInt(100000), ran.nextInt(100000));
		return request;
	}
	
	private static AppendResponse createAppendResponse() {
		AppendResponse response = new AppendResponse(RaftResponse.Status.OK, 
				new RaftError(RaftError.Type.COMMAND_FAILURE, "here is an error!!"), 
				ran.nextLong(), false, ran.nextLong());
		return response;
	}
	
	private static JoinResponse createJoinResponse() {
		ArrayList<RaftMember> members = new ArrayList<>();
		members.add(new RaftMember(MemberId.from(UUID.randomUUID().toString()), RaftMember.Type.ACTIVE, System.currentTimeMillis()));
		members.add(new RaftMember(MemberId.from(UUID.randomUUID().toString()), RaftMember.Type.ACTIVE, System.currentTimeMillis()));
		members.add(new RaftMember(MemberId.from(UUID.randomUUID().toString()), RaftMember.Type.ACTIVE, System.currentTimeMillis()));
		JoinResponse response = new JoinResponse(RaftResponse.Status.OK, 
				new RaftError(RaftError.Type.COMMAND_FAILURE, "here is an error!!"), 
				ran.nextLong(), ran.nextLong(), ran.nextLong(), members);
		return response;
	}
	
	private static LeaveResponse createLeaveResponse() {
		ArrayList<RaftMember> members = new ArrayList<>();
		members.add(new RaftMember(MemberId.from(UUID.randomUUID().toString()), RaftMember.Type.ACTIVE, System.currentTimeMillis()));
		members.add(new RaftMember(MemberId.from(UUID.randomUUID().toString()), RaftMember.Type.ACTIVE, System.currentTimeMillis()));
		members.add(new RaftMember(MemberId.from(UUID.randomUUID().toString()), RaftMember.Type.ACTIVE, System.currentTimeMillis()));
		LeaveResponse response = new LeaveResponse(RaftResponse.Status.OK, 
				new RaftError(RaftError.Type.COMMAND_FAILURE, "here is an error!!"), 
				ran.nextLong(), ran.nextLong(), ran.nextLong(), members);
		return response;
	}
	
	private static ReconfigureResponse createReconfigureResponse() {
		ArrayList<RaftMember> members = new ArrayList<>();
		members.add(new RaftMember(MemberId.from(UUID.randomUUID().toString()), RaftMember.Type.ACTIVE, System.currentTimeMillis()));
		members.add(new RaftMember(MemberId.from(UUID.randomUUID().toString()), RaftMember.Type.ACTIVE, System.currentTimeMillis()));
		members.add(new RaftMember(MemberId.from(UUID.randomUUID().toString()), RaftMember.Type.ACTIVE, System.currentTimeMillis()));
		ReconfigureResponse response = new ReconfigureResponse(RaftResponse.Status.OK, 
				new RaftError(RaftError.Type.COMMAND_FAILURE, "here is an error!!"), 
				ran.nextLong(), ran.nextLong(), ran.nextLong(), members);
		return response;
	}
	
	private static ConfigureResponse createConfigureResponse() {
		ConfigureResponse response = new ConfigureResponse(RaftResponse.Status.OK, 
				new RaftError(RaftError.Type.COMMAND_FAILURE, "here is an error!!"));
		return response;
	}
	
	private static InstallResponse createInstallResponse() {
		InstallResponse response = new InstallResponse(RaftResponse.Status.OK, 
				new RaftError(RaftError.Type.COMMAND_FAILURE, "here is an error!!"));
		return response;
	}
	
	private static KeepAliveResponse createKeepAliveResponse() {
		ArrayList<MemberId> members = new ArrayList<>();
		members.add(MemberId.from(UUID.randomUUID().toString()));
		members.add(MemberId.from(UUID.randomUUID().toString()));
		members.add(MemberId.from(UUID.randomUUID().toString()));
		long[] sessionIds = new long[] {ran.nextInt(100000), ran.nextInt(100000), ran.nextInt(100000), ran.nextInt(100000)};
		KeepAliveResponse response = new KeepAliveResponse(RaftResponse.Status.OK, 
				new RaftError(RaftError.Type.COMMAND_FAILURE, "here is an error!!"), 
				MemberId.from(UUID.randomUUID().toString()), members, sessionIds);
		return response;
	}
	
	private static MetadataResponse createMetadataResponse() {
		HashSet<SessionMetadata> sessions = new HashSet<>();
		sessions.add(new SessionMetadata(ran.nextInt(100000), UUID.randomUUID().toString(), UUID.randomUUID().toString()));
		sessions.add(new SessionMetadata(ran.nextInt(100000), UUID.randomUUID().toString(), UUID.randomUUID().toString()));
		sessions.add(new SessionMetadata(ran.nextInt(100000), UUID.randomUUID().toString(), UUID.randomUUID().toString()));
		sessions.add(new SessionMetadata(ran.nextInt(100000), UUID.randomUUID().toString(), UUID.randomUUID().toString()));
		sessions.add(new SessionMetadata(ran.nextInt(100000), UUID.randomUUID().toString(), UUID.randomUUID().toString()));
		MetadataResponse response = new MetadataResponse(RaftResponse.Status.OK, 
				new RaftError(RaftError.Type.COMMAND_FAILURE, "here is an error!!"), sessions);
		return response;
	}
	
	private static OpenSessionResponse createOpenSessionResponse() {
		OpenSessionResponse response = new OpenSessionResponse(RaftResponse.Status.OK, 
				new RaftError(RaftError.Type.COMMAND_FAILURE, "here is an error!!"),
				ran.nextLong(), ran.nextLong());
		return response;
	}
	
	private static PollResponse createPollResponse() {
		PollResponse response = new PollResponse(RaftResponse.Status.OK, 
				new RaftError(RaftError.Type.COMMAND_FAILURE, "here is an error!!"),
				ran.nextLong(), false);
		return response;
	}
	
	private static CloseSessionResponse createCloseSessionResponse() {
		CloseSessionResponse response = new CloseSessionResponse(RaftResponse.Status.OK, 
				new RaftError(RaftError.Type.COMMAND_FAILURE, "here is an error!!"));
		return response;
	}
	
	private static CommandResponse createCommandResponse() {
		byte[] bs = new byte[1 * 1024];
		Arrays.fill(bs, (byte)2);
		CommandResponse response = new CommandResponse(RaftResponse.Status.OK, 
				new RaftError(RaftError.Type.COMMAND_FAILURE, "here is an error!!"),
				ran.nextLong(), ran.nextLong(), bs, ran.nextLong());
		return response;
	}
	
	private static QueryResponse createQueryResponse() {
		byte[] bs = new byte[1 * 1024];
		Arrays.fill(bs, (byte)2);
		QueryResponse response = new QueryResponse(RaftResponse.Status.OK, 
				new RaftError(RaftError.Type.COMMAND_FAILURE, "here is an error!!"),
				ran.nextLong(), ran.nextLong(), bs, ran.nextLong());
		return response;
	}
	
	private static TransferResponse createTransferResponse() {
		TransferResponse response = new TransferResponse(RaftResponse.Status.OK, 
				new RaftError(RaftError.Type.COMMAND_FAILURE, "here is an error!!"));
		return response;
	}
	
	private static VoteResponse createVoteResponse() {
		VoteResponse response = new VoteResponse(RaftResponse.Status.OK, 
				new RaftError(RaftError.Type.COMMAND_FAILURE, "here is an error!!"),
				ran.nextLong(), false);
		return response;
	}
	
	public static class VoteResponseCreator implements RaftResponseCreator {
		public static final VoteResponseCreator INSTANCE = new VoteResponseCreator();
		@Override
		public RaftResponse create() {
			return RequestResponseCreator.createVoteResponse();
		}
    }
	
	public static class TransferResponseCreator implements RaftResponseCreator {
		public static final TransferResponseCreator INSTANCE = new TransferResponseCreator();
		@Override
		public RaftResponse create() {
			return RequestResponseCreator.createTransferResponse();
		}
    }
	
	public static class QueryResponseCreator implements RaftResponseCreator {
		public static final QueryResponseCreator INSTANCE = new QueryResponseCreator();
		@Override
		public RaftResponse create() {
			return RequestResponseCreator.createQueryResponse();
		}
    }
	
	public static class CommandResponseCreator implements RaftResponseCreator {
		public static final CommandResponseCreator INSTANCE = new CommandResponseCreator();
		@Override
		public RaftResponse create() {
			return RequestResponseCreator.createCommandResponse();
		}
    }
	
	public static class CloseSessionResponseCreator implements RaftResponseCreator {
		public static final CloseSessionResponseCreator INSTANCE = new CloseSessionResponseCreator();
		@Override
		public RaftResponse create() {
			return RequestResponseCreator.createCloseSessionResponse();
		}
    }
	
	public static class PollResponseCreator implements RaftResponseCreator {
		public static final PollResponseCreator INSTANCE = new PollResponseCreator();
		@Override
		public RaftResponse create() {
			return RequestResponseCreator.createPollResponse();
		}
    }
	
	public static class OpenSessionResponseCreator implements RaftResponseCreator {
		public static final OpenSessionResponseCreator INSTANCE = new OpenSessionResponseCreator();
		@Override
		public RaftResponse create() {
			return RequestResponseCreator.createOpenSessionResponse();
		}
    }
	
	public static class MetadataResponseCreator implements RaftResponseCreator {
		public static final MetadataResponseCreator INSTANCE = new MetadataResponseCreator();
		@Override
		public RaftResponse create() {
			return RequestResponseCreator.createMetadataResponse();
		}
    }
	
	public static class KeepAliveResponseCreator implements RaftResponseCreator {
		public static final KeepAliveResponseCreator INSTANCE = new KeepAliveResponseCreator();
		@Override
		public RaftResponse create() {
			return RequestResponseCreator.createKeepAliveResponse();
		}
    }
	
	public static class InstallResponseCreator implements RaftResponseCreator {
		public static final InstallResponseCreator INSTANCE = new InstallResponseCreator();
		@Override
		public RaftResponse create() {
			return RequestResponseCreator.createInstallResponse();
		}
    }
	
	public static class ConfigureResponseCreator implements RaftResponseCreator {
		public static final ConfigureResponseCreator INSTANCE = new ConfigureResponseCreator();
		@Override
		public RaftResponse create() {
			return RequestResponseCreator.createConfigureResponse();
		}
    }
	
	public static class ReconfigureResponseCreator implements RaftResponseCreator {
		public static final ReconfigureResponseCreator INSTANCE = new ReconfigureResponseCreator();
		@Override
		public RaftResponse create() {
			return RequestResponseCreator.createReconfigureResponse();
		}
    	
    }
	
	public static class LeaveResponseCreator implements RaftResponseCreator {
		public static final LeaveResponseCreator INSTANCE = new LeaveResponseCreator();
		@Override
		public RaftResponse create() {
			return RequestResponseCreator.createLeaveResponse();
		}
    	
    }
	
	public static class JoinResponseCreator implements RaftResponseCreator {
		public static final JoinResponseCreator INSTANCE = new JoinResponseCreator();
		@Override
		public RaftResponse create() {
			return RequestResponseCreator.createJoinResponse();
		}
    	
    }
	
	public static class AppendResponseCreator implements RaftResponseCreator {
		public static final AppendResponseCreator INSTANCE = new AppendResponseCreator();
		@Override
		public RaftResponse create() {
			return RequestResponseCreator.createAppendResponse();
		}
    	
    }

}
