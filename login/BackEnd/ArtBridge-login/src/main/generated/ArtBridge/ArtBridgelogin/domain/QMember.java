package ArtBridge.ArtBridgelogin.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = 634511669L;

    public static final QMember member = new QMember("member1");

    public final ListPath<ArtistHomepageComment, QArtistHomepageComment> artistHomepageComments = this.<ArtistHomepageComment, QArtistHomepageComment>createList("artistHomepageComments", ArtistHomepageComment.class, QArtistHomepageComment.class, PathInits.DIRECT2);

    public final ListPath<ArtistMentionComment, QArtistMentionComment> artistMentionComments = this.<ArtistMentionComment, QArtistMentionComment>createList("artistMentionComments", ArtistMentionComment.class, QArtistMentionComment.class, PathInits.DIRECT2);

    public final ListPath<ArtBridge.ArtBridgelogin.domain.Connection.AuctionLike, ArtBridge.ArtBridgelogin.domain.Connection.QAuctionLike> auctionLikes = this.<ArtBridge.ArtBridgelogin.domain.Connection.AuctionLike, ArtBridge.ArtBridgelogin.domain.Connection.QAuctionLike>createList("auctionLikes", ArtBridge.ArtBridgelogin.domain.Connection.AuctionLike.class, ArtBridge.ArtBridgelogin.domain.Connection.QAuctionLike.class, PathInits.DIRECT2);

    public final ListPath<AuctionPointDetail, QAuctionPointDetail> auctionPointDetails = this.<AuctionPointDetail, QAuctionPointDetail>createList("auctionPointDetails", AuctionPointDetail.class, QAuctionPointDetail.class, PathInits.DIRECT2);

    public final BooleanPath isArtist = createBoolean("isArtist");

    public final ListPath<MemberAuctionBidding, QMemberAuctionBidding> memberAuctionBiddings = this.<MemberAuctionBidding, QMemberAuctionBidding>createList("memberAuctionBiddings", MemberAuctionBidding.class, QMemberAuctionBidding.class, PathInits.DIRECT2);

    public final StringPath memberContact = createString("memberContact");

    public final DateTimePath<java.time.LocalDateTime> memberCreatedDate = createDateTime("memberCreatedDate", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> memberDeletedDate = createDateTime("memberDeletedDate", java.time.LocalDateTime.class);

    public final StringPath memberEmail = createString("memberEmail");

    public final StringPath memberId = createString("memberId");

    public final BooleanPath memberIsDeleted = createBoolean("memberIsDeleted");

    public final StringPath memberName = createString("memberName");

    public final StringPath memberNickname = createString("memberNickname");

    public final NumberPath<Long> memberPoint = createNumber("memberPoint", Long.class);

    public final StringPath memberPwd = createString("memberPwd");

    public final NumberPath<Long> memberSeq = createNumber("memberSeq", Long.class);

    public final ListPath<OrderDetail, QOrderDetail> orderDetails = this.<OrderDetail, QOrderDetail>createList("orderDetails", OrderDetail.class, QOrderDetail.class, PathInits.DIRECT2);

    public final ListPath<Review, QReview> reviews = this.<Review, QReview>createList("reviews", Review.class, QReview.class, PathInits.DIRECT2);

    public final ListPath<ArtBridge.ArtBridgelogin.domain.Connection.SaleLike, ArtBridge.ArtBridgelogin.domain.Connection.QSaleLike> saleLikes = this.<ArtBridge.ArtBridgelogin.domain.Connection.SaleLike, ArtBridge.ArtBridgelogin.domain.Connection.QSaleLike>createList("saleLikes", ArtBridge.ArtBridgelogin.domain.Connection.SaleLike.class, ArtBridge.ArtBridgelogin.domain.Connection.QSaleLike.class, PathInits.DIRECT2);

    public final ListPath<Wish, QWish> wishes = this.<Wish, QWish>createList("wishes", Wish.class, QWish.class, PathInits.DIRECT2);

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

