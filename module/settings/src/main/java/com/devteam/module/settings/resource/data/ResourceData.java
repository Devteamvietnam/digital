package com.devteam.module.settings.resource.data;

import com.devteam.module.common.ClientInfo;
import com.devteam.module.settings.resource.entity.ResourceEntity;
import com.devteam.module.settings.resource.entity.ResourceTag;
import com.devteam.module.settings.resource.entity.ResourceType;

public class ResourceData extends ModuleEntityAssert {

  public ResourceEntity BACH_KHOA_UNIVERSITY, VIET_NAM_UNIVERSITY, HARVARD_UNIVERSITY, OXFORD_UNIVERSITY;

  public ResourceEntity ARSTERDAM_HIGH_SCHOOL, HSGS_HIGH_SCHOOL;

  public ResourceEntity OTHER_INFO, SOURCE, PHONE_INTERVIEW, FACE_TO_FACE_INTERVIEW, NEGOCIATION;

  public ResourceEntity ENGLISH, JAPAN, CHINESE, HINDI, PORTUGUESE, GERMAN, MALAYSIAN, GREEK;

  public ResourceEntity EDUCATION, HISTORY, JOURNALISM, PHILOSOPHY, PSYCHOLOGY, BIOLOGY, COMPUTER_SCIENCE;

  public ResourceEntity MOTHER, FATHER;

  public ResourceType   SCHOOL, RELATIONSHIP, MAJOR, LANGUAGE, APPLICANT_PHASE;

  public ResourceTag    LOCAL_UNIVERSITY, FOREIGN_UNIVERSITY, HIGH_SCHOOL;

  protected void initialize(ClientInfo client) {

    LOCAL_UNIVERSITY = new ResourceTag("local-university", "Dai Hoc Trong Nuoc");

    FOREIGN_UNIVERSITY = new ResourceTag("foreign-university", "Dai Hoc Nuoc Ngoai");

    HIGH_SCHOOL = new ResourceTag("high-school", "Trung Hoc Pho Thong");

    SCHOOL = new ResourceType("school", "Truong Hoc").withResourceTag(LOCAL_UNIVERSITY, FOREIGN_UNIVERSITY, HIGH_SCHOOL);

    RELATIONSHIP = new ResourceType("relationship", "Moi quan he");

    MAJOR = new ResourceType("major", "Chuyên ngành");

    LANGUAGE = new ResourceType("language", "Ngôn ngữ");

    APPLICANT_PHASE = new ResourceType("applicantPhase", "Các giai đoạn");

    BACH_KHOA_UNIVERSITY = new ResourceEntity("bach-khoa-university", "Dai Hoc Bach Khoa").withType(SCHOOL.getType())
        .withTag(LOCAL_UNIVERSITY);

    VIET_NAM_UNIVERSITY = new ResourceEntity("dhqg-university", "Dai Hoc Quoc Gia").withType(SCHOOL.getType())
        .withTag(LOCAL_UNIVERSITY);

    HARVARD_UNIVERSITY = new ResourceEntity("harvard-university", "Dai Hoc Harvard").withType(SCHOOL.getType())
        .withTag(FOREIGN_UNIVERSITY);

    OXFORD_UNIVERSITY = new ResourceEntity("oxford-university", "Dai Hoc Oxford").withType(SCHOOL.getType())
        .withTag(FOREIGN_UNIVERSITY);

    ARSTERDAM_HIGH_SCHOOL = new ResourceEntity("armsterdam-high-school", "THPT Armsterdam").withType(SCHOOL.getType());

    HSGS_HIGH_SCHOOL = new ResourceEntity("hsgs-high-school", "THPT Chuyen KHTN").withType(SCHOOL.getType())
        .withTag(HIGH_SCHOOL);

    MOTHER = new ResourceEntity("mother-relation", "Mother").withType(RELATIONSHIP.getType());

    FATHER = new ResourceEntity("father-relation", "Father").withType(RELATIONSHIP.getType());

    EDUCATION = new ResourceEntity("Education", "EDUCATION").withType(MAJOR.getType());
    HISTORY = new ResourceEntity("History", "HISTORY").withType(MAJOR.getType());
    JOURNALISM = new ResourceEntity("Journalism", "JOURNALISM").withType(MAJOR.getType());
    PHILOSOPHY = new ResourceEntity("Philosophy", "PHILOSOPHY").withType(MAJOR.getType());
    PSYCHOLOGY = new ResourceEntity("Psychology", "PSYCHOLOGY").withType(MAJOR.getType());
    BIOLOGY = new ResourceEntity("Biology", "BIOLOGY").withType(MAJOR.getType());
    COMPUTER_SCIENCE = new ResourceEntity("computer_science", "COMPUTER_SCIENCE").withType(MAJOR.getType());

    ENGLISH = new ResourceEntity("English", "ENGLISH").withType(LANGUAGE.getType());
    JAPAN = new ResourceEntity("Japan", "JAPAN").withType(LANGUAGE.getType());
    CHINESE = new ResourceEntity("Chinese", "CHINESE").withType(LANGUAGE.getType());
    HINDI = new ResourceEntity("Hindi", "HINDI").withType(LANGUAGE.getType());
    PORTUGUESE = new ResourceEntity("Portuguese", "PORTUGUESE").withType(LANGUAGE.getType());
    GERMAN = new ResourceEntity("German", "GERMAN").withType(LANGUAGE.getType());
    MALAYSIAN = new ResourceEntity("Malaysian", "MALAYSIAN").withType(LANGUAGE.getType());
    GREEK = new ResourceEntity("Greek", "GREEK").withType(LANGUAGE.getType());

    OTHER_INFO = new ResourceEntity("OTHER_INFO", "Other info").withType(APPLICANT_PHASE.getType());
    SOURCE = new ResourceEntity("SOURCE", "source").withType(APPLICANT_PHASE.getType());
    PHONE_INTERVIEW = new ResourceEntity("PHONE_INTERVIEW", "phone interview").withType(APPLICANT_PHASE.getType());
    FACE_TO_FACE_INTERVIEW = new ResourceEntity("FACE_TO_FACE_INTERVIEW", "face to face interview")
        .withType(APPLICANT_PHASE.getType());
    NEGOCIATION = new ResourceEntity("NEGOCIATION", "negociation").withType(APPLICANT_PHASE.getType());

    ResourceTag[] allResourceTag = new ResourceTag[] { LOCAL_UNIVERSITY, FOREIGN_UNIVERSITY, HIGH_SCHOOL };

    ResourceType[] allResourceType = new ResourceType[] { SCHOOL, RELATIONSHIP, MAJOR, LANGUAGE, APPLICANT_PHASE };

    ResourceEntity[] allResourceEntity = new ResourceEntity[] { BACH_KHOA_UNIVERSITY, VIET_NAM_UNIVERSITY,
        HARVARD_UNIVERSITY, OXFORD_UNIVERSITY, ARSTERDAM_HIGH_SCHOOL, HSGS_HIGH_SCHOOL, MOTHER, FATHER, ENGLISH, JAPAN,
        CHINESE, HINDI, PORTUGUESE, GERMAN, MALAYSIAN, GREEK, EDUCATION, HISTORY, JOURNALISM, PHILOSOPHY, PSYCHOLOGY,
        BIOLOGY, COMPUTER_SCIENCE, OTHER_INFO, SOURCE, PHONE_INTERVIEW, FACE_TO_FACE_INTERVIEW, NEGOCIATION };

    for (ResourceTag tag : allResourceTag) {
      service.saveResourceTag(client, tag);
    }

    for (ResourceType type : allResourceType) {
      service.saveResourceType(client, type);
    }

    for (ResourceEntity resEntity : allResourceEntity) {
      service.saveResourceEntity(client, resEntity);
    }
  }

  public void assertResourceEntity() {
    new ResourceEntityAssert(client, BACH_KHOA_UNIVERSITY, SCHOOL.getType()).assertEntityCreated().assertEntityUpdate()
        .assertEntitySearch().assertEntityArchive();
  }

  public void assertResourceType() {
    new ResourceTypeAssert(client, SCHOOL).assertEntityCreated().assertEntityUpdate().assertEntitySearch()
        .assertEntityArchive();
  }

  public void assertResourceTypeDelete() {
    ResourceTypeAssert asserter = new ResourceTypeAssert(client, SCHOOL);
    asserter.testDeleteTag(BACH_KHOA_UNIVERSITY.getCode());
  }
}
