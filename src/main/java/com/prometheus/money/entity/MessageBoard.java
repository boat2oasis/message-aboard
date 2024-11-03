package com.prometheus.money.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author Heisenberg
 * @since 2024-11-02
 */
@Getter
@Setter
@TableName("message_board")
public class MessageBoard implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String nickname;

    private String qq;

    private String email;

    private String content;

    private Boolean isReplied;

    private String replyContent;

    private String replyBy;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
