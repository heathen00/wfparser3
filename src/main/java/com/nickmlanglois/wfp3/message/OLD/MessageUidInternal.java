package com.nickmlanglois.wfp3.message.OLD;

import com.nickmlanglois.uid.Uid;

interface MessageUidInternal extends Uid<Message> {
  Message getMessage();
}
