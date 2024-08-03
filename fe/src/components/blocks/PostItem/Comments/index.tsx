'use client';

import { getPostApi, GetPostResponseDto } from '@/apis/post';
import { Textfield } from '@/components/atoms/Textfield';
import { ChangeEvent, useContext, useState } from 'react';
import * as styles from './index.css';
import { Button } from '@/components/atoms/Button';
import { useCreateComment } from '@/apis/comments/comments.hooks';
import { ProfileIcon } from '@/components/atoms/ProfileIcon';
import { Typography } from '@/components/atoms/Typography';
import { PostItemContext } from '../PostItem';

export default function Comments() {
  const context = useContext(PostItemContext);

  if (context === null) {
    throw new Error('PostItemContext is null');
  }

  const { post, setPost } = context;
  const id = post.id;
  const comments = post.comments.comments;

  const [input, setInput] = useState('');

  const { createCommentMutate } = useCreateComment();

  /** 댓글 input 변경 */
  const handleChangeInput = (e: ChangeEvent<HTMLInputElement>) => {
    setInput(e.target.value);
  };

  /** 댓글 등록 */
  const handleCreateComment = () => {
    createCommentMutate(
      { id, content: input },
      {
        onSuccess: () => {
          setInput('');
          refetch();
        },
      },
    );
  };

  /** refetch */
  const refetch = async () => {
    const data = await getPostApi(id);
    setPost(data.data.data);
  };

  return (
    <div className={styles.wrap}>
      {comments?.map((comment) => (
        <div className={styles.commentWrap} key={comment.id}>
          <ProfileIcon size="small" profilePath={comment.profilePath} id={comment.memberId} />
          <Typography>{comment.content}</Typography>
        </div>
      ))}

      <div className={styles.inputWrap}>
        <Textfield
          style={{ flex: 1 }}
          value={input}
          placeholder="댓글을 입력해주세요"
          onChange={handleChangeInput}
        />
        <Button text="등록" onClick={handleCreateComment} />
      </div>
    </div>
  );
}
