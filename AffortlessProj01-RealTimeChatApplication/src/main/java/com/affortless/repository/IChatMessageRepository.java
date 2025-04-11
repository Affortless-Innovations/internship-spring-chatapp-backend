package com.affortless.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.affortless.entity.RealTimeMessage;

public interface IChatMessageRepository extends JpaRepository<RealTimeMessage, Long> 
{
	public List<RealTimeMessage> findBySenderIdAndReceiverIdOrReceiverIdAndSenderIdOrderByCreationTimeAsc(String sender1,String receiver1,String sender2,String receiver2 );
}
