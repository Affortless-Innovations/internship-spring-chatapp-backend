package com.affortless.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.affortless.entity.RealTimeMessage;

public interface IChatMessageRepository extends JpaRepository<RealTimeMessage, Long> 
{
	@Query("SELECT m FROM RealTimeMessage m WHERE " +
		       "(m.senderId = :sender1 AND m.receiverId = :receiver1) OR " +
		       "(m.senderId = :receiver1 AND m.receiverId = :sender1) " +
		       "ORDER BY m.creationTime ASC")
		List<RealTimeMessage> getChatHistoryBetweenUsers(@Param("sender1") String sender1,
		                                                  @Param("receiver1") String receiver1);

}
