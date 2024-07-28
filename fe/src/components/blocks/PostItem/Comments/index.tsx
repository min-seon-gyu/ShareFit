'use client';

import { GetPostResponseDto } from '@/apis/post';
import { Textfield } from '@/components/atoms/Textfield';
import { ChangeEvent, useState } from 'react';
import * as styles from './index.css';
import { Button } from '@/components/atoms/Button';
import { useCreateComment } from '@/apis/comments/comments.hooks';
import { ProfileIcon } from '@/components/atoms/ProfileIcon';
import { Typography } from '@/components/atoms/Typography';

type Props = {
  id: number;
  data: GetPostResponseDto;
};

export default function Comments({ id, data }: Props) {
  const [input, setInput] = useState('');

  const comments = data.comments.comments;

  const { createCommentMutate } = useCreateComment();

  const handleChangeInput = (e: ChangeEvent<HTMLInputElement>) => {
    setInput(e.target.value);
  };

  const handleCreateComment = () => {
    createCommentMutate(
      { id, content: input },
      {
        onSuccess: () => {
          setInput('');
        },
      },
    );
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
