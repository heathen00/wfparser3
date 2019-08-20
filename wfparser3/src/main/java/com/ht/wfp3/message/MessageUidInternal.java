package com.ht.wfp3.message;

import com.ht.uid.Uid;

interface MessageUidInternal extends Uid<Message> {
  Message getMessage();
}
