package com.sanqing.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sanqing.bean.Channel;
import com.sanqing.dao.ChannelDAO;
import com.sanqing.util.DBConnection;

/**
 * 
 * @author Damon
 *
 */
public class ChannelDAOImpl implements ChannelDAO {

	@Override
	public void addChannel(Channel channel) {
		Connection conn = DBConnection.getConnection();	//������Ӷ���
		String addSQL = "insert into " +
				"tb_channel(channelName) values(?)";
		PreparedStatement pstmt = null;					//����Ԥ�������
		try {
			pstmt = conn.prepareStatement(addSQL);		//���һ��Ԥ��������Ҹ�ֵ
			pstmt.setString(1, channel.getChannelName());
			pstmt.executeUpdate();						//ִ�����
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(pstmt);					//�ر�Ԥ�������
			DBConnection.close(conn);					//�ر����Ӷ���
		}
	}

	@Override
	public List<Channel> getAllChannel() {
		Connection conn = DBConnection.getConnection();	//������Ӷ���
		String sql = "select * from " + 
				"tb_channel";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Channel> channels = new ArrayList<Channel>();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Channel channel = new Channel();
				channel.setChannelID(rs.getInt("channelID"));
				channel.setChannelName(rs.getString("channelName"));
				channels.add(channel);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(rs);
			DBConnection.close(pstmt);
			DBConnection.close(conn);
		}
		return channels;
	}

}
